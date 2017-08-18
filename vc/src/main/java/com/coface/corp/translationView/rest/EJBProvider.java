package com.coface.corp.translationView.rest;

import java.lang.reflect.Type;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.core.spi.component.ComponentScope;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.InjectableProvider;

@Provider
public class EJBProvider implements InjectableProvider<EJB, Type>
{
  private static final Logger slf4jLogger = LoggerFactory.getLogger(I18nBundleEndpoint.class);

  @Override
  public Injectable<?> getInjectable(ComponentContext cc, EJB ejb, Type t)
  {
    if (!(t instanceof Class))
      return null;
    try
    {
      final Object o = new InitialContext().lookup("i18nAPI#" + ((Class<?>) t).getCanonicalName());
      return new Injectable<Object>()
      {
        @Override
        public Object getValue()
        {
          return o;
        }
      };
    }
    catch (Exception e)
    {
      slf4jLogger.error(e.getStackTrace().toString());
      return null;
    }
  }

  @Override
  public ComponentScope getScope()
  {
    return ComponentScope.Singleton;
  }
}
