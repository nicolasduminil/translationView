package com.coface.corp.translationView.session;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.coface.corp.translationView.model.I18nBundle;
import com.coface.corp.translationView.model.I18nLanguage;
import com.coface.corp.translationView.model.Message;
import com.coface.corp.translationView.model.MessageId;
import com.coface.corp.translationView.model.Translation;
import com.coface.corp.translationView.model.TranslationId;
import com.coface.corp.translationView.model.UserApplication;
import com.coface.corp.translationView.model.UserApplicationId;
import com.coface.corp.translationView.model.UserData;
import com.coface.corp.translationView.model.UserDataId;
import com.coface.corp.translationView.model.UserLanguage;
import com.coface.corp.translationView.model.UserLanguageId;

@Stateless(mappedName = "i18nAPI")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class I18nSLSBImpl implements I18nSLSB
{
  @PersistenceContext(unitName = "i18nPU")
  private EntityManager em;

  private Query findAllBundles = null;
  private Query findAllMessages = null;
  private Query findAllLanguages = null;
  private Query findAllTranslations = null;
  private Query findAllUserApplications = null;
  private Query findAllUserData = null;
  private Query findAllUserLanguages = null;
  private Query findBundleByUserApplicationIdQuery = null;

  @SuppressWarnings("unchecked")
  @Override
  public List<I18nBundle> listAllBundles(Integer startPosition, Integer maxResult)
  {
    Query findAllQuery = getFindAllBundlesQuery();
    if (startPosition != null)
      findAllQuery.setFirstResult(startPosition);
    if (maxResult != null)
      findAllQuery.setMaxResults(maxResult);
    return findAllQuery.getResultList();
  }

  @Override
  public void createBundle(I18nBundle bundle)
  {
    em.persist(bundle);
  }

  @Override
  public Boolean deleteBundleById(String id)
  {
    Boolean rslt = Boolean.FALSE;
    I18nBundle entity = em.find(I18nBundle.class, id);
    if (entity != null)
    {
      em.remove(entity);
      rslt = Boolean.TRUE;
    }
    return rslt;
  }

  @Override
  public I18nBundle findBundleById(String id)
  {
    return em.find(I18nBundle.class, id);
  }

  @Override
  public void updateBundle(I18nBundle bundle)
  {
    em.merge(bundle);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Message> listAllMessages(Integer startPosition, Integer maxResult)
  {
    Query findAllQuery = getFindAllMessagesQuery();
    if (startPosition != null)
      findAllQuery.setFirstResult(startPosition);
    if (maxResult != null)
      findAllQuery.setMaxResults(maxResult);
    return findAllQuery.getResultList();
  }

  @Override
  public void createMessage(Message message)
  {
    em.persist(message);
  }

  @Override
  public Boolean deleteMessageById(MessageId id)
  {
    Boolean rslt = Boolean.FALSE;
    Message entity = em.find(Message.class, id);
    if (entity != null)
    {
      em.remove(entity);
      rslt = Boolean.TRUE;
    }
    return rslt;
  }

  @Override
  public Message findMessageById(MessageId id)
  {
    return em.find(Message.class, id);
  }

  @Override
  public void updateMessage(Message message)
  {
    em.merge(message);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Translation> listAllTranslations(Integer startPosition, Integer maxResult)
  {
    Query findAllQuery = getFindAllTranslationsQuery();
    if (startPosition != null)
      findAllQuery.setFirstResult(startPosition);
    if (maxResult != null)
      findAllQuery.setMaxResults(maxResult);
    return findAllQuery.getResultList();
  }

  @Override
  public void createTranslation(Translation translation)
  {
    em.persist(translation);
  }

  @Override
  public Boolean deleteTranslationById(TranslationId id)
  {
    Boolean rslt = Boolean.FALSE;
    Translation entity = em.find(Translation.class, id);
    if (entity != null)
    {
      em.remove(entity);
      rslt = Boolean.TRUE;
    }
    return rslt;
  }

  @Override
  public Translation findTranslationById(TranslationId id)
  {
    return em.find(Translation.class, id);
  }

  @Override
  public void updateTranslation(Translation translation)
  {
    em.merge(translation);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<I18nLanguage> listAllLanguages(Integer startPosition, Integer maxResult)
  {
    Query findAllQuery = getFindAllLanguagesQuery();
    if (startPosition != null)
      findAllQuery.setFirstResult(startPosition);
    if (maxResult != null)
      findAllQuery.setMaxResults(maxResult);
    return findAllQuery.getResultList();
  }

  @Override
  public void createLanguage(I18nLanguage language)
  {
    em.persist(language);
  }

  @Override
  public Boolean deleteLanguageById(String id)
  {
    Boolean rslt = Boolean.FALSE;
    I18nLanguage entity = em.find(I18nLanguage.class, id);
    if (entity != null)
    {
      em.remove(entity);
      rslt = Boolean.TRUE;
    }
    return rslt;
  }

  @Override
  public I18nLanguage findLanguageById(String id)
  {
    return em.find(I18nLanguage.class, id);
  }

  @Override
  public void updateLanguage(I18nLanguage language)
  {
    em.merge(language);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<UserApplication> listAllUserApplications(Integer startPosition, Integer maxResult)
  {
    Query findAllQuery = getFindAllUserApplicationsQuery();
    if (startPosition != null)
      findAllQuery.setFirstResult(startPosition);
    if (maxResult != null)
      findAllQuery.setMaxResults(maxResult);
    return findAllQuery.getResultList();
  }

  @Override
  public void createUserApplication(UserApplication userApplication)
  {
    em.persist(userApplication);
  }

  @Override
  public Boolean deleteUserApplicationById(UserApplicationId id)
  {
    Boolean rslt = Boolean.FALSE;
    UserApplication entity = em.find(UserApplication.class, id);
    if (entity != null)
    {
      em.remove(entity);
      rslt = Boolean.TRUE;
    }
    return rslt;
  }

  @Override
  public UserApplication findUserApplicationById(UserApplicationId id)
  {
    return em.find(UserApplication.class, id);
  }

  @Override
  public void updateUserApplication(UserApplication userApplication)
  {
    em.merge(userApplication);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<UserLanguage> listAllUserLanguages(Integer startPosition, Integer maxResult)
  {
    Query findAllQuery = getFindAllUserLanguagesQuery();
    if (startPosition != null)
      findAllQuery.setFirstResult(startPosition);
    if (maxResult != null)
      findAllQuery.setMaxResults(maxResult);
    return findAllQuery.getResultList();
  }

  @Override
  public void createUserLanguage(UserLanguage userLanguage)
  {
    em.persist(userLanguage);
  }

  @Override
  public Boolean deleteUserLanguageById(UserLanguageId id)
  {
    Boolean rslt = Boolean.FALSE;
    UserLanguage entity = em.find(UserLanguage.class, id);
    if (entity != null)
    {
      em.remove(entity);
      rslt = Boolean.TRUE;
    }
    return rslt;
  }

  @Override
  public UserLanguage findUserLanguageById(UserLanguageId id)
  {
    return em.find(UserLanguage.class, id);
  }

  @Override
  public void updateUserLanguage(UserLanguage userLanguage)
  {
    em.merge(userLanguage);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<UserData> listAllUserData(Integer startPosition, Integer maxResult)
  {
    Query findAllQuery = getFindAllUserDataQuery();
    if (startPosition != null)
      findAllQuery.setFirstResult(startPosition);
    if (maxResult != null)
      findAllQuery.setMaxResults(maxResult);
    return findAllQuery.getResultList();
  }

  @Override
  public void createUserData(UserData userData)
  {
    em.persist(userData);
  }

  @Override
  public Boolean deleteUserDataById(UserDataId id)
  {
    Boolean rslt = Boolean.FALSE;
    UserData entity = em.find(UserData.class, id);
    if (entity != null)
    {
      em.remove(entity);
      rslt = Boolean.TRUE;
    }
    return rslt;
  }

  @Override
  public UserData findUserDataById(UserDataId id)
  {
    return em.find(UserData.class, id);
  }

  @Override
  public void updateUserData(UserData userData)
  {
    em.merge(userData);
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<I18nBundle> findBundleByUserApplicationId(UserApplicationId userApplicationId)
  {
    this.findUserApplicationById(userApplicationId);
    Query findQuery = getFindBundleByUserApplicationIdQuery();
    findQuery.setParameter("codePk", this.findUserApplicationById(userApplicationId).getId());
    return findQuery.getResultList();
  }

  private Query getFindAllBundlesQuery()
  {
    if (findAllBundles == null)
      findAllBundles = em.createNamedQuery("Bundle.findAll");
    return findAllBundles;
  }

  private Query getFindAllMessagesQuery()
  {
    if (findAllMessages == null)
      findAllMessages = em.createNamedQuery("Message.findAll");
    return findAllMessages;
  }

  private Query getFindAllTranslationsQuery()
  {
    if (findAllTranslations == null)
      findAllTranslations = em.createNamedQuery("Translation.findAll");
    return findAllTranslations;
  }

  private Query getFindAllUserApplicationsQuery()
  {
    if (findAllUserApplications == null)
      findAllUserApplications = em.createNamedQuery("UserApplication.findAll");
    return findAllUserApplications;
  }

  private Query getFindAllUserDataQuery()
  {
    if (findAllUserData == null)
      findAllUserData = em.createNamedQuery("UserData.findAll");
    return findAllUserData;
  }

  private Query getFindAllUserLanguagesQuery()
  {
    if (findAllUserLanguages == null)
      findAllUserLanguages = em.createNamedQuery("UserLanguage.findAll");
    return findAllUserLanguages;
  }

  private Query getFindAllLanguagesQuery()
  {
    if (findAllLanguages == null)
      findAllLanguages = em.createNamedQuery("Language.findAll");
    return findAllLanguages;
  }

  private Query getFindBundleByUserApplicationIdQuery()
  {
    if (findBundleByUserApplicationIdQuery == null)
      findBundleByUserApplicationIdQuery = em.createNamedQuery("Bundle.findByUser");
    return findAllLanguages;
  }
}
