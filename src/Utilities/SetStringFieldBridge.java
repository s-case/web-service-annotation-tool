package Utilities;

import java.util.HashSet;
import java.util.Set;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.hibernate.search.bridge.LuceneOptions;
import org.hibernate.search.bridge.TwoWayFieldBridge;

public class SetStringFieldBridge implements TwoWayFieldBridge {
   
   @Override
   public void set(String name, Object value, Document document, LuceneOptions luceneOptions) {
      
      if ( value == null ) {
         return;
      }
      
      // we expect a Set<String> here. checking for Set for simplicity
      if ( ! (value instanceof Set )) {
         throw new IllegalArgumentException("support limited to Set<String>");
      }
      
      @SuppressWarnings("unchecked")
      Set<String> set = (Set<String>)value;
      
      for (String string : set) {
         Field field = new Field(name, string, luceneOptions.getStore(), luceneOptions.getIndex(), luceneOptions.getTermVector());
         field.setBoost(luceneOptions.getBoost());
         document.add(field);
      }
      
   }

   @Override
   public Object get(String name, Document document) {
      Field[] fields = document.getFields(name);
      Set<String> set = new HashSet<String>();
      for (Field field : fields) {
         set.add(field.stringValue());
      }
      return set;
   }

   @Override
   public String objectToString(Object value) {
      if ( value == null ) {
         return "";
      } else if ( value instanceof String ) {
         return (String) value;
      } else {
         return String.valueOf(value);
      }
   }
   
   

}