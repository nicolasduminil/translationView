package com.coface.corp.translationView.session;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Pattern;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.rpc.ServiceException;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
import com.coface.corp.translationView.utils.export.ExcelBundleDataModel;
import com.coface.corp.translationView.utils.export.ExportContext;
import com.coface.corp.translationView.utils.imports.ImportResult;
import com.coface.corp.translationView.utils.imports.TranslationSheet;

@Stateless(mappedName = "i18nAPI")
public class I18nSLSBImpl implements I18nSLSB
{
  private static Logger slf4jLogger = LoggerFactory.getLogger(I18nSLSBImpl.class);

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
  private Query findAllWithDefaultEmptyValueQuery = null;
  private Query findAllMessagesWithNoTranslations = null;
  private Query findMessagesByBundle = null;
  private Query findTranslationByByBundleandLanguage = null;

  @SuppressWarnings("unchecked")
  @Override
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
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
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
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
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
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
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
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
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
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
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
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
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
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
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
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
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
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
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
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
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
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
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
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
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
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
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
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
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
  public List<I18nBundle> findBundleByUserApplicationId(UserApplicationId userApplicationId)
  {
    Query findQuery = getFindBundleByUserApplicationIdQuery();
    findQuery.setParameter("codePk", this.findUserApplicationById(userApplicationId).getId());
    return findQuery.getResultList();
  }

  @SuppressWarnings("unchecked")
  @Override
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
  public List<Translation> findAllWithDefaultEmptyValue(String bundleId, String langId)
  {
    Query findQuery = getfindAllWithDefaultEmptyValue();
    findQuery.setParameter("bundleId", bundleId);
    findQuery.setParameter("langId", langId);
    return findQuery.getResultList();
  }

  @SuppressWarnings("unchecked")
  @Override
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
  public List<Message> findAllMessagesWithNoTranslations(String id, String langId)
  {
    Query findQuery = this.getFindAllMessagessWithNoTranslations();
    findQuery.setParameter("msgId", id);
    findQuery.setParameter("lang", langId);
    return findQuery.getResultList();
  }

  @SuppressWarnings("unchecked")
  @Override
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
  public List<Message> findMessagesByBundle(String codeApp)
  {
    Query findQuery = this.getFindMessagesByBundle();
    findQuery.setParameter("codeApp", codeApp);
    return findQuery.getResultList();
  }

  @SuppressWarnings("unchecked")
  @Override
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
  public List<Translation> findTranslationsByBundleAndLanguage(String codeApp, String lang)
  {
    Query findQuery = this.getFindTranslationByByBundleandLanguage();
    findQuery.setParameter("msgId", codeApp);
    findQuery.setParameter("lang", lang);
    return findQuery.getResultList();
  }

  @Override
  @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
  public ExportContext generateXlsFile(String[] bundleIds, String[] languageIds) throws Exception
  {
    ExportContext exportContext = new ExportContext();
    File folder = exportContext.createTempDirectory();
    FileOutputStream xlsFileOutputStream = null;
    try
    {
      ExcelBundleDataModel excelBundleModel = getExcelBundleDataSet(bundleIds, languageIds);
      HSSFWorkbook workBook = createWorkBook(excelBundleModel);
      File xlsFile = new File(folder + File.separator + "exportTranslations.xls");
      xlsFileOutputStream = new FileOutputStream(xlsFile);
      workBook.write(xlsFileOutputStream);
      exportContext.setFilePath(xlsFile.getAbsolutePath());
      exportContext.setResultFile(xlsFile);
      return exportContext;
    }
    catch (Exception e)
    {
      exportContext.releaseResources();
      throw new ServiceException("generatePropertiesJarFile failed for applicationBundleIds=" + bundleIds, e);

    }
    finally
    {
      if (xlsFileOutputStream != null)
      {
        try
        {
          xlsFileOutputStream.flush();
          xlsFileOutputStream.close();
        }
        catch (IOException e)
        {
        }
      }
    }
  }

  @Override
  public Map<I18nBundle, Map<I18nLanguage, Properties>> loadXLSDataFileContent(final InputStream xlsFileData) throws Exception
  {
    Map<I18nBundle, Map<I18nLanguage, Properties>> map = new HashMap<I18nBundle, Map<I18nLanguage, Properties>>();
    POIFSFileSystem fs = new POIFSFileSystem(xlsFileData);
    HSSFWorkbook wb = new HSSFWorkbook(fs);
    int nbApplicationBundles = wb.getNumberOfSheets();
    for (int sheetIndex = 0; sheetIndex < nbApplicationBundles; sheetIndex++)
    {
      HSSFSheet sheet = wb.getSheetAt(sheetIndex);
      String sheetName = wb.getSheetName(sheetIndex);
      if (!isValid(sheetName))
        slf4jLogger.error("Fail to validate sheet name " + sheetName);
      else
      {
        I18nBundle bundle = findBundleById(sheetName);
        if (bundle == null)
          slf4jLogger.error("Failed to find bundle with name " + sheetName);
        else if (bundle.getIsActive().equals("0"))
          slf4jLogger.error("Bundle with name " + sheetName + " is not active");
        TranslationSheet translationSheet = new TranslationSheet(sheet, sheetName);
        translationSheet.processSheet(this.listAllLanguages(null, null));
        bundle.setCodePk(wb.getSheetName(sheetIndex));
        map.put(bundle, translationSheet.getTranslationsMap());
      }
    }
    return map;
  }

  @Override
  public ImportResult importProperties(Map<I18nBundle, Map<I18nLanguage, Properties>> xlsData, boolean isCreateMissingBundles) throws Exception
  {
    ImportResult result = new ImportResult();
    for (I18nBundle applicationBundle : xlsData.keySet())
    {
      Map<I18nLanguage, Properties> propertiesMap = xlsData.get(applicationBundle);
      for (I18nLanguage language : propertiesMap.keySet())
        result.addAll(importProperties(applicationBundle.getCodePk(), language, propertiesMap.get(language), isCreateMissingBundles));
    }
    return result;
  }

  private ImportResult importProperties(String applicationBundleId, I18nLanguage language, Properties properties, boolean isCreateMissingBundles) throws Exception
  {
    ImportResult result = new ImportResult();
    if (isCreateMissingBundles)
      this.createBundleIfItDoesntExist(applicationBundleId);
    createMessages(applicationBundleId, language, properties);
    updateTranslations(applicationBundleId, language, properties);
    createTranslations(applicationBundleId, language, properties);
    return result;
  }

  private void createTranslations(String applicationBundleId, I18nLanguage language, Properties inProperties) throws Exception
  {
    List<Message> referenceKeysForMissingTranslations = this.findAllMessagesWithNoTranslations(applicationBundleId, language.getCodePk());
    for (Message message : referenceKeysForMissingTranslations)
      if (inProperties.containsKey(message.getId().getTagId()))
      {
        String value = (String) inProperties.get(message.getId().getTagId());
        if (value != null)
          value = value.trim();
        Translation translation = new Translation(new TranslationId(message.getId().getTagId(), applicationBundleId, language.getCodePk()), language, null, new Date(), "validated");
        this.createTranslation(translation);
      }
  }

  private void updateTranslations(String applicationBundleId, I18nLanguage language, Properties inProperties) throws Exception
  {
    List<Translation> referenceExistingTranslations = this.findTranslationsByBundleAndLanguage(applicationBundleId, language.getCodePk());

    for (Translation translation : referenceExistingTranslations)
    {
      if (inProperties.containsKey(translation.getId().getTagId()))
      {
        String newValue = (String) inProperties.get(translation.getId().getTagId());
         if (newValue != null)
          newValue = newValue.trim();
        if (translation.getValue() != null && !translation.getValue().equals(newValue))
        {
          translation.setStatus("validated");
          translation.setLastUpdate(new Date());
          translation.setValue(newValue);
          translation.setNewValue("");
          translation.setComments("");
          updateTranslation(translation);
        }
      }
    }
  }

  private void createMessages(String applicationBundleId, I18nLanguage language, Properties inProperties) throws Exception
  {
    @SuppressWarnings({ "unchecked", "rawtypes" })
    Set<String> keys = new HashMap<String, String>((Map) inProperties).keySet();
    final List<Message> referenceExistingMessages = findMessagesByBundle(applicationBundleId);
    CollectionUtils.forAllDo(referenceExistingMessages, doForMessages(inProperties));
    CollectionUtils.forAllDo(keys, doForProperties(referenceExistingMessages, applicationBundleId));
  }

  public void createBundleIfItDoesntExist(String applicationBundleId) throws Exception
  {
    I18nBundle bundle = this.findBundleById(applicationBundleId);
    if (bundle == null)
      this.createBundle(new I18nBundle(applicationBundleId, applicationBundleId, "1"));
    else if (bundle.getIsActive().equals("0"))
    {
      bundle.setIsActive("1");
      updateBundle(bundle);
    }
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

  private Query getfindAllWithDefaultEmptyValue()
  {
    if (findAllWithDefaultEmptyValueQuery == null)
      findAllWithDefaultEmptyValueQuery = em.createNamedQuery("Translation.findAllWithDefaultEmptyValue");
    return findAllWithDefaultEmptyValueQuery;
  }

  private Query getFindAllMessagessWithNoTranslations()
  {
    if (findAllMessagesWithNoTranslations == null)
      findAllMessagesWithNoTranslations = em.createNamedQuery("Message.findAllWithNoTranslations");
    return findAllMessagesWithNoTranslations;
  }

  private Query getFindMessagesByBundle()
  {
    if (findMessagesByBundle == null)
      findMessagesByBundle = em.createNamedQuery("Message.findMessagesByBundle");
    return findMessagesByBundle;
  }

  private Query getFindTranslationByByBundleandLanguage()
  {
    if (findTranslationByByBundleandLanguage == null)
      findTranslationByByBundleandLanguage = em.createNamedQuery("Translation.findTranslationByByBundleandLanguage");
    return findTranslationByByBundleandLanguage;
  }

  
  private ExcelBundleDataModel getExcelBundleDataSet(String[] bundleIds, String[] languageIds) throws Exception
  {
    ExcelBundleDataModel bundleDataSet = new ExcelBundleDataModel();
    List<Translation> translations = new ArrayList<Translation>();
    for (String bundleId : bundleIds)
      for (String languageId : languageIds)
      {
        translations = findAllWithDefaultEmptyValue(bundleId, languageId);
        bundleDataSet.feedWithLocalizedTranslations(bundleId, languageId, translations);
      }
    return bundleDataSet;
  }

  private HSSFWorkbook createWorkBook(ExcelBundleDataModel excelBundleModel) throws Exception
  {
    HSSFWorkbook wb = new HSSFWorkbook();
    List<String> applications = excelBundleModel.getApplications();
    for (String application : applications)
    {
      HSSFSheet sheet = wb.createSheet(application);
      HSSFRow headerRow = null;
      headerRow = sheet.createRow(0);
      HSSFCell keyCell = headerRow.createCell(0);
      HSSFRichTextString hssfRichTextString = new HSSFRichTextString("key");
      keyCell.setCellValue(hssfRichTextString);
      HSSFFont font = wb.createFont();
      font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
      HSSFCellStyle headerCellStyle = wb.createCellStyle();
      headerCellStyle.setFont(font);
      headerCellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
      headerCellStyle.setFillBackgroundColor(new HSSFColor.AQUA().getIndex());
      keyCell.setCellStyle(headerCellStyle);
      int columnHeaderIdx = 1;
      for (String aLanguage : excelBundleModel.getLocalesAvailables())
      {
        HSSFCell localeHeaderCell = headerRow.createCell(columnHeaderIdx++);
        localeHeaderCell.setCellValue(new HSSFRichTextString(aLanguage));
        localeHeaderCell.setCellStyle(headerCellStyle);
      }

      List<String> tagIdAvailablesForApplication = excelBundleModel.getTagIdAvailablesFor(application);
      int bodyRowIdx = 1;
      for (String tagId : tagIdAvailablesForApplication)
      {
        HSSFRow row = null;
        row = sheet.createRow(bodyRowIdx++);
        row.createCell(0).setCellValue(new HSSFRichTextString(tagId));
        excelBundleModel.getTranslationsFor(application, tagId);
        List<String> translationsForTagId = excelBundleModel.getTranslationsFor(application, tagId);
        for (int i = 0, j = 1; i < translationsForTagId.size(); i++, j++)
        {
          row.createCell(j).setCellValue(new HSSFRichTextString(translationsForTagId.get(i)));
        }
      }
    }
    return wb;
  }

  private Boolean isValid(String sheetName)
  {
    return (Pattern.compile("^([a-zA-Z0-9]+)$").matcher(sheetName).matches());
  }

  private Closure doForMessages(final Properties props)
  {
    Closure closure = new Closure()
    {
      @Override
      public void execute(Object o)
      {
        Message msg = (Message)o;
        if (msg.getSuppressed().contentEquals("1") && props.containsKey(msg.getId().getTagId()))
          createMessage(msg);
      }
    };
    return closure;
  }

  private Closure doForProperties(final List<Message> msgs, final String bundleId)
  {
    Closure closure = new Closure()
    {
      @Override
      public void execute(Object o)
      {
        String key = (String)o;
        boolean found = false;
        for (Message msg : msgs)
        {
          if (key.equals(msg.getId().getTagId()))
          {
            found = true;
            break;
          }
        }
        if (!found)
        {
          Message message = new Message(new MessageId(key, bundleId), new I18nBundle(bundleId));
          message.setSuppressed("0");
          createMessage(message);
        }
      }
    };
    return closure;
  }
}
