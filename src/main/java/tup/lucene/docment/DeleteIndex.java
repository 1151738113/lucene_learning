package tup.lucene.docment;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import tup.lucene.analyzer.IKAnalyzer6x;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by wei.wang on 2018/2/8.
 */
public class DeleteIndex {

  public static void main(String[] args){
  deleteDoc("title","美国");
  }

  private static void deleteDoc(String field, String key){
    Analyzer analyzer = new IKAnalyzer6x();
    IndexWriterConfig icw = new IndexWriterConfig(analyzer);
    Path indexPath = Paths.get("indexdir");
    Directory directory;
    try{
      directory = FSDirectory.open(indexPath);
      IndexWriter indexWriter = new IndexWriter(directory,icw);
      indexWriter.deleteDocuments(new Term(field, key));
      indexWriter.commit();
      indexWriter.close();
      System.out.println("删除完成");
    }catch(Exception e){
    e.printStackTrace();
    }

  }

}
