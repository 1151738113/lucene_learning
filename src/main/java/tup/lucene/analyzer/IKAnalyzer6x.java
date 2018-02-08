package tup.lucene.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;

/**
 * Created by wei.wang on 2018/2/7.
 */
public class IKAnalyzer6x extends Analyzer {
  private boolean useSmart;
  public boolean isUseSmart(){
    return useSmart;
  }
  public void setUseSmart(boolean useSmart){
    this.useSmart = useSmart;
  }
  public IKAnalyzer6x(){
    this(false);
  }
  public IKAnalyzer6x(boolean useSmart){
    super();
    this.useSmart = useSmart;
  }

  protected TokenStreamComponents createComponents(String s) {
    Tokenizer _IKTokenizer = new IKTokenizer6x(this.isUseSmart());
    return new TokenStreamComponents(_IKTokenizer);
  }
}
