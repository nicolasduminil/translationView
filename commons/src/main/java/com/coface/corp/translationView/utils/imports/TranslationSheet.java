package com.coface.corp.translationView.utils.imports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import com.coface.corp.translationView.model.I18nLanguage;

public class TranslationSheet
{
  private static final Log LOG = LogFactory.getLog(TranslationSheet.class);
  private final static String KEY_VALUE = "key";

  private HSSFSheet sheet;
  private String sheetName;

  private Map<I18nLanguage, Properties> translationsMap = new HashMap<I18nLanguage, Properties>();
  private Map<Integer, I18nLanguage> languageRowMapper = new HashMap<Integer, I18nLanguage>();

  public TranslationSheet(HSSFSheet sheet, String sheetName)
  {
    this.sheet = sheet;
    this.sheetName = sheetName;
  }

  public Map<I18nLanguage, Properties> getTranslationsMap()
  {
    return translationsMap;
  }

  public void processSheet(List<I18nLanguage> acceptedLanguageList)
  {
    LOG.debug("Inspecting sheet " + sheetName);
    if (isHeaderSheetOK(sheet.getRow(0), sheetName, acceptedLanguageList))
      for (int i = 1; i <= sheet.getLastRowNum(); i++)
        if (sheet.getRow(i) != null)
          processRow(sheet.getRow(i), sheetName);
        else
          LOG.error("Fail to get row number " + i + " from " + sheetName);
  }

  private boolean isHeaderSheetOK(final HSSFRow row, final String bundleName, List<I18nLanguage> acceptedLanguageList)
  {
    Map<String, I18nLanguage> availableLanguageMap = new HashMap<String, I18nLanguage>();
    for (I18nLanguage language : acceptedLanguageList)
    {
      availableLanguageMap.put(language.getCodePk(), language);
    }

    if (row == null)
    {
      LOG.error("Row is null for sheet " + sheetName);
      return false;
    }

    final HSSFCell cell = row.getCell(0);
    if (cell == null)
    {
      LOG.error("Cell is null for sheet " + sheetName);
      return false;
    }
    if (!KEY_VALUE.equalsIgnoreCase(cell.getRichStringCellValue().toString()))
    {
      LOG.error("Cell value is not key for " + sheetName);
      return false;
    }

    int i = 1;
    while (row.getCell(i) != null)
    {
      final String languageId = row.getCell(i).getRichStringCellValue().toString();
      if (StringUtils.isBlank(languageId))
        if (!availableLanguageMap.containsKey(languageId))
        {
          LOG.error("Cell number " + i + " for row number " + row.getRowNum() + " is empty");
          return false;
        }
      I18nLanguage lang = new I18nLanguage();
      lang.setCodePk(languageId);
      translationsMap.put(lang, new Properties());
      languageRowMapper.put(i, lang);
      i++;
    }
    return true;
  }

  private void processRow(final HSSFRow row, final String sheetName)
  {
    if (this.isRowValid(row))
    {
      String tagId = getElementTagId(row, sheetName);
      int nbTranslationColumns = languageRowMapper.size();
      for (int i = 1; i <= nbTranslationColumns; i++)
      {
        final HSSFCell cell = row.getCell(i);
        if ((cell != null))
        {
          cell.setCellType(HSSFCell.CELL_TYPE_STRING);
          final HSSFRichTextString richStringCellValue = cell.getRichStringCellValue();
          final String cellValue = richStringCellValue.toString();
          Properties properties = translationsMap.get(languageRowMapper.get(i));
          if (validateValue(cell))
            properties.put(tagId, cellValue);
        }
        else
          LOG.error("Cell number " + i + " for row number " + row.getRowNum() + " is empty");
      }
    }
  }

  private String getElementTagId(HSSFRow row, String sheetName)
  {
    HSSFCell cell2 = row.getCell(0);
    cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
    // cell2.setCellType(HSSFCell.CELL_TYPE_STRING);
    switch (cell2.getCellType())
    {
    case HSSFCell.CELL_TYPE_NUMERIC:
      return "" + cell2.getNumericCellValue();
    case HSSFCell.CELL_TYPE_STRING:
      return "" + cell2.getRichStringCellValue().toString();
    default:
      throw new IllegalArgumentException("Unexpected cell type " + cell2.getCellType());
    }
  }

  private boolean isRowValid(final HSSFRow row)
  {
    if ((row == null) || (row.getRowNum() == 0))
    {
      return false;
    }
    final HSSFCell cell = row.getCell(0);

    if ((cell == null) || (HSSFCell.CELL_TYPE_STRING == cell.getCellType() && "".equals(cell.getRichStringCellValue().toString().trim())))
    {
      return false;
    }
    return true;
  }

  private boolean validateValue(final HSSFCell cell)
  {
    if ((cell != null))
    {
      cell.setCellType(HSSFCell.CELL_TYPE_STRING);
      String value = cell.getRichStringCellValue().toString();
      if (StringUtils.isBlank(value))
      {
        LOG.error("Sheet name " + sheetName + " row number " + cell.getRow().getRowNum() + " cell number " + cell.getCellNum() + " is empty");
        return false;
      }
      else
        return true;
    }
    else
    {
      return false;
    }
  }
}
