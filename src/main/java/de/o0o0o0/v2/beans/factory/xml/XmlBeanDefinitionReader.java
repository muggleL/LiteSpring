package de.o0o0o0.v2.beans.factory.xml;

import de.o0o0o0.utils.ClassUtils;
import de.o0o0o0.v1.beans.factory.BeanDefinitionStoreException;
import de.o0o0o0.v2.beans.BeanDefinition;
import de.o0o0o0.v2.beans.factory.support.BeanDefinitionRegister;
import de.o0o0o0.v2.beans.factory.support.GenericBeanDefinition;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/*
读取 xml 配置文件 存入 beanDefinition 和 存入 BeanFactory 的 BeanDefinitionMap
 */
public class XmlBeanDefinitionReader {
    public static final String ATTRIBUTE_ID = "id";
    public static final String ATTRIBUTE_CLASS = "class";
    BeanDefinitionRegister register;

    public XmlBeanDefinitionReader(BeanDefinitionRegister register) {
        this.register = register;
    }
    public void LoadBeanDefinitionReader(String configFile){
        InputStream stream = null;
        ClassLoader cl = ClassUtils.getDefaultClassLoader();
        stream = cl.getResourceAsStream(configFile);
        SAXReader reader = new SAXReader();
        try {
            Document doc = reader.read(stream);

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
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
