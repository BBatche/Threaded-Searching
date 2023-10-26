import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 *
 * @author bcb24
 */
public class threads {

        public static void main(String[] args) {
        String searchType = args[0];
		String mode = args[0];
        String[] keyWords = new String[args.length - 1];
        System.arraycopy(args, 1, keyWords, 0, args.length-1);
        
		
        if (mode.equals("s")) {
            searchSingleThread(keyWords);
        } else if (mode.equals("m")) {
            searchMultiThread(keyWords);
        } else {
            System.out.println("Invalid mode. Use 's' for single thread or 'm' for multiple threads.");
        }
    }
    
    public static void searchSingleThread(String[] keyWords){
		for(String keyword: keyWords){
			try{BufferedReader reader = new BufferedReader(new FileReader("bible.txt"));
            
                int wordCount = 0;
                String line;
                while((line = reader.readLine()) != null){
                    if(line.toLowerCase().contains(keyword)){
                        wordCount++;
                    }
                }
                System.out.println("Found " + wordCount + " lines that contain " + keyword);
				reader.close();
            }
			catch(IOException e){
            System.out.println("Error reading the file");
			}
		}
            
    }//end SearchSingleThreaded
    
    
    public static void searchMultiThread(String[] keyWords){
        for (String keyword : keyWords) {
            Thread searchThread = new Thread(() -> {
                try (BufferedReader reader = new BufferedReader(new FileReader("bible.txt"))) {
                    int wordCount = 0;
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.toLowerCase().contains(keyword)) {
                            wordCount++;
                        }
                    }
                    System.out.println("Found " + wordCount + " lines that contain " + keyword);
                } catch (IOException e) {
                    System.err.println("Error reading the file");
                }
            });
            searchThread.start();
        }
    }//endSearchMultiThreaded
    
    
}//end Threads
