package GameManagement;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Paths;
import java.util.ArrayList;

public class ChallangeModeMapper extends CommonMapper{


    //properties
    private ArrayList<ImageView> imageList;
    private SolutionDatabase solutionDatabase;
    private String[] additionalBlockNames = {"n1","n2","n3","n4","n5","n6","n7","n8","n9","n10","n11","n12"};
    
    // constructor
    public ChallangeModeMapper(String password){
        imageList = new ArrayList<>();
        solutionDatabase = new SolutionDatabase(password);
        setGame(getCurrentLevel());
    }

    @Override
    ArrayList<ImageView> getInitialImageList() {
        return imageList;
    }

    @Override
    void setInitialImageList() {
    	int currentLevel = getCurrentLevel();
        imageList.clear();
        System.out.println( "setInitialImage: "+ currentLevel );
        String str;
        System.out.println( solutionDatabase );
        ArrayList<String> solutionList = solutionDatabase.getSolution( "NormalMode", currentLevel, 1);
        addTwoBlocks(additionalBlockNames,solutionList);
        shuffle(solutionList);
        for( int i = 0; i < solutionList.size(); i++ )
        {
            str = "src/GameManagement/media/" + solutionList.get(i) + ".png";
            Image block = new Image( Paths.get(str).toUri().toString());
            ImageView blockView = new ImageView(block);
            imageList.add(blockView);
        }

    }
    
    void addTwoBlocks(String[] blocks, ArrayList<String> solution) {
		int random = 0;
		for(int addedBlocks = 0; addedBlocks < 2; addedBlocks++)
		{
			random = (int)(Math.random() * 12);
			if(! solution.contains( blocks[random] )) 
				solution.add(blocks[random]);
			else
				addedBlocks--;
		}
	}
    
    void shuffle(ArrayList<String> solution) {
    	for(int i = 0; i < 20; i++)
    	{	
    		int random = (int)(Math.random()*solution.size());
    		int secRandom = (int)(Math.random()*solution.size());
    		String temp = solution.get(random);
    		solution.set(random, solution.get(secRandom));
    		solution.set(secRandom, temp);
    	}
    }
    
    

}
