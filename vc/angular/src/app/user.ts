export class User {
  accountId: number;
  displayedName: string;
  
  canValidate:boolean;
  canTranslate:boolean;
  canDoImport:boolean;
  canDeleteTranslation:boolean;
  canRefreshCache:boolean;
  canManageBundles:boolean;
  canManageLanguages:boolean;
  canManageMessages:boolean;
  hasAllLanguagues:boolean;
  hasAllBundles:boolean;
  languages;
  bundles;
  supervisor:boolean;
}
