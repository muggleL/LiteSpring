package de.o0o0o0.v7.beans.factory.xml;

import de.o0o0o0.utils.StringUtils;
import de.o0o0o0.v7.beans.BeanDefinition;
import de.o0o0o0.v7.beans.PropertyValue;
import de.o0o0o0.v7.beans.factory.support.BeanDefinitionRegister;
import de.o0o0o0.v7.beans.factory.support.GenericBeanDefinition;
import de.o0o0o0.v7.config.RuntimeBeanReference;
import de.o0o0o0.v7.config.TypedStringValue;
import de.o0o0o0.v7.core.io.Resource;
import de.o0o0o0.v1.beans.factory.BeanDefinitionStoreException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/*
读取 xml 配置文件 存入 beanDefinition 和 存入 BeanFactory 的 BeanDefinitionMap
 */
public class XmlBeanDefinitionReader {
    private static final String ATTRIBUTE_ID = "id";
    private static final String ATTRIBUTE_CLASS = "class";
    private static final String ATTRIBUTE_SCOPE = "scope";
    private static final String ELEMENT_PROPERTY = "property";
    private static final String ATTRIBUTE_REF = "ref";
    private static final String ATTRIBUTE_VALUE = "value";
    private static final String ATTRIBUTE_NAME = "name";

    private BeanDefinitionRegister register;
    protected final Log logger = LogFactory.getLog(this.getClass());

    public XmlBeanDefinitionReader(BeanDefinitionRegister register) {
        this.register = register;
    }

    public void LoadBeanDefinitionReader(Resource resource) {
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(inputStream);

            Element root = doc.getRootElement();
            Iterator iterator = root.elementIterator();
            while (iterator.hasNext()) {
                Element el = (Element) iterator.next();
                String id = el.attributeValue(ATTRIBUTE_ID);
                String beanClassName = el.attributeValue(ATTRIBUTE_CLASS);
                BeanDefinition bd = new GenericBeanDefinition(id, beanClassName);
                if (el.attribute(ATTRIBUTE_SCOPE) != null) {
                    String scope = el.attributeValue(ATTRIBUTE_SCOPE);
                    bd.setScope(scope);
                }
                parsePropertyElement(el, bd);
                this.register.registerBeanDefinition(id, bd);
            }
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("load configFile failed", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 解析
    private void parsePropertyElement(Element element, BeanDefinition definition) {
        Iterator iterator = element.elementIterator(ELEMENT_PROPERTY);
        while (iterator.hasNext()) {
            Element ele = (Element) iterator.next();
            String propertyName = ele.attributeValue(ATTRIBUTE_NAME);
            if (!StringUtils.hasLength(propertyName)) {
                logger.fatal("Tag 'properties' must have a 'name' attribute");
                return;
            }

            Object val = parsePropertyValue(ele, definition, propertyName);
            PropertyValue pv = new PropertyValue(propertyName, val);
            definition.getPropertyValues().add(pv);
        }
    }

    private Object parsePropertyValue(Element ele, BeanDefinition definition, String propertyName) {
        String elementName = (propertyName != null) ?
                "<property> element for property '" + propertyName + "'" :
                "<constructor-arg> element";
        boolean hasRefAttribute = ele.attribute(ATTRIBUTE_REF) != null;
        boolean hasValueAttribute = ele.attribute(ATTRIBUTE_VALUE) != null;

        if (hasRefAttribute) {
            String refName = ele.attributeValue(ATTRIBUTE_REF);
            if (!StringUtils.hasText(refName)) {
                logger.error(elementName + " contains empty 'ref' attribute");
            }
            return new RuntimeBeanReference(refName);
        } else if (hasValueAttribute) {
            TypedStringValue value;
            value = new TypedStringValue(ele.attributeValue(ATTRIBUTE_VALUE));
            return value;
        }else {
            throw new RuntimeException(elementName + "must specify a ref or value");
        }
    }
}
