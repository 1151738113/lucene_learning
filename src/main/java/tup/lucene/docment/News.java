package tup.lucene.docment;

/**
 * Created by wei.wang on 2018/2/8.
 * 仿照lucene的存储原理，实现一个简单的文档索引构建
 */
public class News {

  private int id;

  private String title;

  private String content;

  private int reply;

  public News(){

  }

  public News(int id, String title, String content, int reply){
    this.id = id;
    this.content = content;
    this.title = title;
    this.reply = reply;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getReply() {
    return reply;
  }

  public void setReply(int reply) {
    this.reply = reply;
  }
}
