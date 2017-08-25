class Language {
  public constructor (options?: {codePk: any; label: any; isActive: any; isReference: any}) {
    if (options) {
      this.codePk = options.codePk;
      this.label = options.label;
      this.isActive = options.isActive;
      this.isReference = options.isReference;
    } 
  }
  public codePk: any;
  public label: any;
  public isActive: any;
  public isReference: any;
}

export default Language;