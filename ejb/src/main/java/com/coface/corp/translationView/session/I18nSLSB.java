package com.coface.corp.translationView.session;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

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
import com.coface.corp.translationView.service.export.ExportContext;

@Local
@Remote
public interface I18nSLSB
{
  public List<I18nBundle> listAllBundles(Integer startPosition, Integer maxResult);

  public void createBundle(I18nBundle bundle);

  public Boolean deleteBundleById(String id);

  public I18nBundle findBundleById(String id);

  public void updateBundle(I18nBundle bundle);

  public List<Message> listAllMessages(Integer startPosition, Integer maxResult);

  public void createMessage(Message message);

  public Boolean deleteMessageById(MessageId id);

  public Message findMessageById(MessageId id);

  public void updateMessage(Message message);

  public List<Translation> listAllTranslations(Integer startPosition, Integer maxResult);

  public void createTranslation(Translation translation);

  public Boolean deleteTranslationById(TranslationId id);

  public Translation findTranslationById(TranslationId id);

  public void updateTranslation(Translation translation);

  public List<I18nLanguage> listAllLanguages(Integer startPosition, Integer maxResult);

  public void createLanguage(I18nLanguage language);

  public Boolean deleteLanguageById(String id);

  public I18nLanguage findLanguageById(String id);

  public void updateLanguage(I18nLanguage language);

  public List<UserApplication> listAllUserApplications(Integer startPosition, Integer maxResult);

  public void createUserApplication(UserApplication userApplication);

  public Boolean deleteUserApplicationById(UserApplicationId id);

  public UserApplication findUserApplicationById(UserApplicationId id);

  public void updateUserApplication(UserApplication userApplication);

  public List<UserLanguage> listAllUserLanguages(Integer startPosition, Integer maxResult);

  public void createUserLanguage(UserLanguage userLanguage);

  public Boolean deleteUserLanguageById(UserLanguageId id);

  public UserLanguage findUserLanguageById(UserLanguageId id);

  public void updateUserLanguage(UserLanguage userLanguage);

  public List<UserData> listAllUserData(Integer startPosition, Integer maxResult);

  public void createUserData(UserData userData);

  public Boolean deleteUserDataById(UserDataId id);

  public UserData findUserDataById(UserDataId id);

  public void updateUserData(UserData userData);

  public List<I18nBundle> findBundleByUserApplicationId(UserApplicationId userApplicationId);

  public List<Translation> findAllWithDefaultEmptyValue(String bundleId, String langId);

  public ExportContext generateXlsFile(String[] bundleIds, String[] languageIds) throws Exception;
}
