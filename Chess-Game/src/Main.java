import Pieces.*;
import BoardGame.*;
import VC.*;


public class Main{

    public static void main(String[]args){
        BoardGame bg = new BoardGame(8,8, true);
        View view = new View();
        Controller controller = new Controller(bg, view);
    }
}