package tup.lucene.analyzer;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;

/**
 * Created by wei.wang on 2018/2/8.
 */
public class IKTokenizer6x  extends Tokenizer{
  //ik分词器实现
  private IKSegmenter _IKImplement;
  //词元文本属性
  private final CharTermAttribute termAtt;
  //词元位移属性
  private final OffsetAttribute offserAtt;
  //词元分类属性
  private final TypeAttribute typeAtt;
  //记录最后一个词元的结束位置
  private int endPostition;

  public IKTokenizer6x(boolean useSmart){
    super();
    offserAtt = addAttribute(OffsetAttribute.class);
    termAtt = addAttribute(CharTermAttribute.class);
    typeAtt = addAttribute(TypeAttribute.class);
    _IKImplement = new IKSegmenter(input, useSmart);
  }

  public boolean incrementToken() throws IOException {
    clearAttributes();    //清楚词元属性
    Lexeme nextLexeme = _IKImplement.next();
    if(nextLexeme != null){
      //将Lexeme转换成Attributes
      termAtt.append(nextLexeme.getLexemeText());   //设置词元文本
      termAtt.setLength(nextLexeme.getLength());    //设置词元长度
      offserAtt.setOffset(nextLexeme.getBeginPosition(),nextLexeme.getEndPosition());   //设置词元位移
      endPostition = nextLexeme.getEndPosition();
      typeAtt.setType(nextLexeme.getLexemeText());    //记录词元分类
      return true;    //返回true告知还有下个词元
    }
    return false;    //返回false告知词元输出结束
  }

  public void reset() throws IOException {
    super.reset();
    _IKImplement.reset(input);
  }

  public final void end(){
    int finalOffset = correctOffset(this.endPostition);
    offserAtt.setOffset(finalOffset,finalOffset);
  }

}
