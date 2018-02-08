package tup.lucene.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by wei.wang on 2018/2/8.
 */
public class ExtDicTest {
  private static String str = "厉害了我的哥，ok，就谈到这啦，源于自己认知的局限性，也许谈的一些地方会有错误之处，欢迎大牛指正。";

  public static void main(String[] args) throws IOException {

    Analyzer analyzer = new IKAnalyzer6x(true);
    StringReader reader = new StringReader(str);
    TokenStream tokenizer = analyzer.tokenStream(str,reader);
    tokenizer.reset();
    CharTermAttribute termAttribute = tokenizer.getAttribute(CharTermAttribute.class);
    while (tokenizer.incrementToken()){
      System.out.print(termAttribute.toString() + " | ");
    }
    analyzer.close();
  }

}
