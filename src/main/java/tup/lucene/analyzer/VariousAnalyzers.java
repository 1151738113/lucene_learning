package tup.lucene.analyzer;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by wei.wang on 2018/2/7.
 */
public class VariousAnalyzers {
  private static String str = "中华人民共和国简称中国，是一个有13亿人口的国家";
  public static void main(String[] args) throws IOException {
    Analyzer analyzer = null;
    //标准分词器
    //空格分词器
    analyzer = new WhitespaceAnalyzer();
    printAnalyer(analyzer);
    //简单分词器
    System.out.println("简单分词器："+analyzer.getClass());
    analyzer = new SimpleAnalyzer();
    printAnalyer(analyzer);
    //二分法分词器
    System.out.println("二分法分词器："+analyzer.getClass());
    analyzer = new CJKAnalyzer();
    printAnalyer(analyzer);
    //关键字分词器
    System.out.println("关键字分词器："+analyzer.getClass());
    analyzer = new KeywordAnalyzer();
    printAnalyer(analyzer);
    //停用词分词器
    System.out.println("停用词分词器："+analyzer.getClass());
    analyzer = new StopAnalyzer();
    printAnalyer(analyzer);
    //中文分词器
    System.out.println("中文分词器："+analyzer.getClass());
    analyzer = new SmartChineseAnalyzer();
    printAnalyer(analyzer);

  }

  private static void printAnalyer(Analyzer analyzer) throws IOException {
    StringReader reader = new StringReader(str);
    TokenStream tokenStream = analyzer.tokenStream(str,reader);
    tokenStream.reset();
    CharTermAttribute charTermAttribute = tokenStream.getAttribute(CharTermAttribute.class);
    while (tokenStream.incrementToken()){
      System.out.print(charTermAttribute.toString()+" | ");
    }
    System.out.print("\n");
    analyzer.close();
  }


}
