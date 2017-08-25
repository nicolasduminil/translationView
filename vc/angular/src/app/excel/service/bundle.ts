class Bundle {
  public constructor (options?: {codePk: any; label: any; isActive: any}) {
    if (options) {
      this.codePk = options.codePk;
      this.label = options.label;
      this.isActive = options.isActive;
    } 
  }
  public codePk: any;
  public label: any;
  public isActive: any;
}

export default Bundle;