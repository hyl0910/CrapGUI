/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene2;

import static java.lang.Math.random;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import java.security.SecureRandom;
import java.util.Scanner;
import java.math.BigDecimal;

/**
 * FXML Controller class
 *
 * @author LauHeiYee
 */
public class GameController implements Initializable {
    public static ArrayList<Block> blockchain =new ArrayList<Block>();
    public static int difficulty=5;
    private static final SecureRandom randomNumbers = new SecureRandom();
    @FXML
    private Button summarybutton;
    @FXML
    private Button history;
    @FXML
    private Button anothergame;
    @FXML
    private TextField total;

    
    private enum Status{CONTINUE, WON, LOST};
    private static final int SNAKE_EYES=2;
    private static final int TREY=3;
    private static final int SEVEN=7;
    private static final int YO_LEVEN=11;
    private static final int BOX_CARS=12;
    private static int frequency1=0;
    private static int frequency2=0;
    private static int frequency3=0;
    private static int frequency4=0;
    private static int frequency5=0;
    private static int frequency6=0;
    
    

    @FXML
    private Button rollbutton;
    @FXML
    private ImageView dice;
    @FXML
    private TextArea summary;
    @FXML
    private TextField win;
    @FXML
    private TextField lose;
    private static int win_num;
    private static int lose_num;
    private static int total_num;
    private static double ratio;
    private static double win_p;
    private static double lose_p;
    private static int sum;
    private static int die1;
    private static int die2;
    private static int count;
            
    
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        win_num = 0;
        lose_num = 0;
        win.setText(String.valueOf(win_num));
        lose.setText(String.valueOf(lose_num));
        
    }    
    
    public void addText(String Text){
        summary.appendText(Text);
    }
    
    
    public int rollDice()
    {
         StringBuilder sb = new StringBuilder();
        
        int die1 =1+randomNumbers.nextInt(6);
        int die2 =1+randomNumbers.nextInt(6);
        
        switch(die1)
        {
            case 1:
                ++frequency1;
                break;
            case 2:
                ++frequency2;
                break;
            case 3:
                ++frequency3;
                break;
            case 4:
                ++frequency4;
                break;
            case 5:
                ++frequency5;
                break;
            case 6:
                ++frequency6;
                break;       
        }
        
        switch(die2)
        {
            case 1:
                ++frequency1;
                break;
            case 2:
                ++frequency2;
                break;
            case 3:
                ++frequency3;
                break;
            case 4:
                ++frequency4;
                break;
            case 5:
                ++frequency5;
                break;
            case 6:
                ++frequency6;
                break;       
        }
        
         int sum=die1+die2;
        
         blockchain.add(new Block("This is the genesis block","0"));
       System.out.println("Trying to Mine block 1...");
       blockchain.get(0).mineBlock(difficulty);
        
        blockchain.add(new Block(String.format("Player rolled %d + %d = %d%n", die1, die2, sum),blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to Mine block 2...");
        blockchain.get(1).mineBlock(difficulty);
        
        blockchain.add(new Block(String.format("Player rolled %d + %d = %d%n", die1, die2, sum),blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to Mine block 3...");
        blockchain.get(2).mineBlock(difficulty);
        
        blockchain.add(new Block(String.format("Player rolled %d + %d = %d%n", die1, die2, sum),blockchain.get(blockchain.size()-1).hash));
        System.out.println("Trying to Mine block 4...");
        blockchain.get(3).mineBlock(difficulty);
        
        
        System.out.println("\nBlockchain is Valid:"+isChainValid());
        
        String blockchainJson=new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println("\nThe blockchain: ");
        System.out.println(blockchainJson);
         
       
        total_num=win_num+lose_num+1;
        
        total.setText(String.valueOf(total_num));
        
       addText( String.format("Player rolled %d + %d = %d%n", die1, die2, sum));
         
         return sum;
 
        }
    
    
     @FXML
    private void rollbuttonaction(ActionEvent event) {    
        summary.setText("");
        
        
        int myPoint =0;
        Status gameStatus;
        
        int sumOfDice = rollDice();
        
        switch(sumOfDice)
        {
            case SEVEN:
            case YO_LEVEN:
                gameStatus=Status.WON;
                break;
            
            case SNAKE_EYES:
            case TREY:
            case BOX_CARS:
                gameStatus=Status.LOST;
                break;
                
            default:
                gameStatus = Status.CONTINUE;
                myPoint=sumOfDice;
                addText( String.format( "Point is %d%n", myPoint));
                ++count;
                break;
        }
        
        while(gameStatus == Status.CONTINUE)
        {
            sumOfDice=rollDice();
            ++count;
            if(sumOfDice==myPoint)
                gameStatus=Status.WON;
            else
                if(sumOfDice ==SEVEN)
                    gameStatus=Status.LOST;
        }
        
        if(gameStatus == Status.WON){
            addText(String.format("Player wins%n"));
            win_num++;
            win.setText(String.valueOf(win_num));}
        else {
            addText(String.format("Player loses%n"));
            lose_num++;
            lose.setText(String.valueOf(lose_num));}

       
    }
       
    
    
        
    @FXML
    private void summarybuttonaction(ActionEvent event) {
        summary.setText("");
        addText(String.format("Face\tFrequency"));
        addText( String.format("%n1\t%d%n2\t%d%n3\t%d%n4\t%d%n5\t%d%n6\t%d%n", frequency1, frequency2, frequency3, frequency4, frequency5, frequency6));
            
    }
    
    @FXML
    private void historybuttonaction(ActionEvent event) {
        summary.setText("");
        
        //ratio=win_num*10/lose_num;
        
        
        addText(String.format("Win-Lose Ratio: "));
        //addText(String.valueOf(ratio));
        win_p=win_num*100/total_num;
        addText(String.format("%nPercentage of Win: "));
        addText(String.valueOf(win_p));
        lose_p=lose_num*100/total_num;
        addText(String.format("%nPercentage of Lose: "));
        addText(String.valueOf(lose_p));
    }
    
    
    @FXML
    private void anotherbuttonaction(ActionEvent event) {
        win.setText("");
        lose.setText("");
        total.setText("");
        summary.setText("");
        win_num=0;
        lose_num=0;
         frequency1=0;
        frequency2=0;
        frequency3=0;
       frequency4=0;
       frequency5=0;
      frequency6=0;
      
    }
    
     
    
    public static Boolean isChainValid(){
        Block currentBlock;
        Block previousBlock;
        String hashTarget=new String(new char[difficulty]).replace('\0','0');
        
        for(int i=1; i<blockchain.size();i++){
            currentBlock=blockchain.get(i);
            previousBlock=blockchain.get(i-1);
            
            if(!currentBlock.hash.equals(currentBlock.calculateHash())){
                System.out.println("Current Hashes not equal");
                return false;
            }
            
            if(!previousBlock.hash.equals(currentBlock.previousHash)){
                System.out.println("Previous Hashes not equal");
                return false;
            }
                
            if(!currentBlock.hash.substring(0,difficulty).equals(hashTarget)){
                System.out.println("This block hasn't been mined");
                return false;
        }
            
    }
    return true;
}
                    }
                
                        
            
        
