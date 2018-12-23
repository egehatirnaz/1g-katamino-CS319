package GameManagement;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.Paths;
import java.util.ArrayList;

public class NormalModeMapper extends CommonMapper {

    private ArrayList<ImageView> imageList;
    private SolutionDatabase solutionDatabase;
    private String password;
    protected int currentLevel;

    public  NormalModeMapper(String password){
        this.currentLevel = 3;
        this.password = password;
        imageList = new ArrayList<>();
        isGameFinished = false;
        setGame(getCurrentLevel());
    }

    @Override
    ArrayList<ImageView> getInitialImageList() {
        return  imageList;
    }

    @Override
    public int getCurrentLevel()
    {
        return currentLevel;
    }

    @Override
    void updateLevel() {
        currentLevel++;
        setGame(currentLevel);
    }

    @Override
    void setInitialImageList() {
        System.out.println( "PASSWORD IN SETINITIALIMAGELIST NORMAL MODE MAPPER: " + password );
        solutionDatabase = new SolutionDatabase(password);
        int currentLevel = getCurrentLevel();
        imageList.clear();
        System.out.println( "setInitialImage: "+ currentLevel );
        String str;
        System.out.println( solutionDatabase );
        ArrayList<String> solutionList = solutionDatabase.getSolution( "NormalMode", currentLevel, 1);

        if(solutionList.size() == 0)
            super.isGameFinished = true;
        else {
            for (int i = 0; i < solutionList.size(); i++) {
                str = "src/GameManagement/media/" + solutionList.get(i) + ".png";
                Image block = new Image(Paths.get(str).toUri().toString());
                ImageView blockView = new ImageView(block);
                imageList.add(blockView);
            }
        }
        solutionDatabase.closeDatabase();
    }




}
