package tup.lucene.docment;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import tup.lucene.analyzer.IKAnalyzer6x;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * Created by wei.wang on 2018/2/8.
 * 根据lucene的倒排模式，模拟一个索引创建方式
 */
public class CreatIndex {
  public static void main(String[] args) throws IOException {

    News news1 = new News();
    news1.setId(1);
    news1.setTitle("习近平会见美国总统奥巴马，学习国外经验");
    news1.setContent("国家主席习近平9月3日在杭州西湖国宾馆会见前来出席二十国集团领导人杭州峰会的美国总统奥巴马。。。");
    news1.setReply(672);

    News news2 = new News();
    news2.setId(2);
    news2.setTitle("北大迎4380名新生 农村学生700多人今年最多");
    news2.setContent("昨天，北大迎来4380名来自全国各地及数十个国家的本科新生。其中，农村学生共700余名，为近年最多...");
    news2.setReply(995);


    //创建分词器
    Analyzer analyzer = new IKAnalyzer6x();
    IndexWriterConfig icw = new IndexWriterConfig(analyzer);
    icw.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
    Directory dir = null;
    IndexWriter indexWriter = null;
    //索引目录
    Path indexPath = Paths.get("indexdir");
    //开始时间
    Date start = new Date();
    dir = FSDirectory.open(indexPath);
    indexWriter = new IndexWriter(dir, icw);
    //设置新闻id索引并存储
    FieldType idType = new FieldType();
    idType.setIndexOptions(IndexOptions.DOCS);
    idType.setStored(true);
    //设置新闻标题索引文档、词项频率、位移信息和偏移量，存储并词条化
    FieldType titleType = new FieldType();
    titleType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
    //存储字段true
    titleType.setStored(true);
    //通过配置的分析器对字段进行解析
    titleType.setTokenized(true);
    FieldType contentType = new FieldType();
    contentType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
    //存储字段true
    contentType.setStored(true);
    //通过配置的分析器对字段进行解析
    contentType.setTokenized(true);
    //Set to true if this field's indexed form should be also stored into term vectors.  存储索引信息
    contentType.setStoreTermVectors(true);
    //True if this field's token positions should also be stored into the term vectors  存储位置信息
    contentType.setStoreTermVectorPositions(true);
    //True if this field's token character offsets should also be stored into term vectors.  存储偏移信息
    contentType.setStoreTermVectorOffsets(true);
    //True if this field's token payloads should also be stored into the term vectors.   存储词频
    contentType.setStoreTermVectorPayloads(true);


    Document doc1 = new Document();
    doc1.add(new Field("id",String.valueOf(news1.getId()),idType));
    doc1.add(new Field("title",news1.getTitle(),titleType));
    doc1.add(new Field("content",news1.getContent(),contentType));
    doc1.add(new IntPoint("reply",news1.getReply()));
    doc1.add(new StoredField("reply_display",news1.getReply()));

    Document doc2 = new Document();
    doc2.add(new Field("id",String.valueOf(news2.getId()),idType));
    doc2.add(new Field("title",news2.getTitle(),titleType));
    doc2.add(new Field("content",news2.getContent(),contentType));
    doc2.add(new IntPoint("reply",news2.getReply()));
    doc2.add(new StoredField("reply_display",news2.getReply()));

    indexWriter.addDocument(doc1);
    indexWriter.addDocument(doc2);
    indexWriter.commit();
    indexWriter.close();
    dir.close();
    Date end = new Date();
    System.out.println("索引文档用时："+(end.getTime()-start.getTime())+" milliseconds");

  }

}
