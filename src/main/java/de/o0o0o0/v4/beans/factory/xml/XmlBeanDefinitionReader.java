package de.o0o0o0.v4.beans.factory.xml;

import de.o0o0o0.utils.ClassUtils;
import de.o0o0o0.v1.beans.factory.BeanDefinitionStoreException;
import de.o0o0o0.v4.beans.BeanDefinition;
import de.o0o0o0.v4.beans.factory.support.BeanDefinitionRegister;
import de.o0o0o0.v4.beans.factory.support.GenericBeanDefinition;
import de.o0o0o0.v4.core.io.Resource;
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
    private BeanDefinitionRegister register;

    public XmlBeanDefinitionReader(BeanDefinitionRegister register) {
        this.register = register;
    }
    public void LoadBeanDefinitionReader(Resource resource){
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
}
