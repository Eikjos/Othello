package Othello.Game;

import java.awt.*;
import java.util.List;

public class Minimax {

    static int nodesExplored = 0;
    private static int[][] BOARD_VALUE = {
            {100, -1, 5, 2, 2, 5, -1, 100},
            {-1, -10,1, 1, 1, 1,-10, -1},
            {5 , 1,  1, 1, 1, 1,  1,  5},
            {2 , 1,  1, 0, 0, 1,  1,  2},
            {2 , 1,  1, 0, 0, 1,  1,  2},
            {5 , 1,  1, 1, 1, 1,  1,  5},
            {-1,-10, 1, 1, 1, 1,-10, -1},
            {100, -1, 5, 2, 2, 5, -1, 100}};


    public static Cell solve(Cell[][] node, Player player,int depth){
        nodesExplored = 0;
        int bestScore = Integer.MIN_VALUE;
        Cell bestMove = null;
        for(Cell move : Board.getPossibilities(node, player)){
            //create new node
            Cell[][] newNode = Board.getNewBoardAfterMove(node ,move,player);
            //recursive call
            int childScore = MMAB(newNode,player,depth-1,false,Integer.MIN_VALUE,Integer.MAX_VALUE);
            if(childScore > bestScore) {
                bestScore = childScore;
                bestMove = move;
            }
        }
        return bestMove;
    }


    //returns minimax value for a given node with A/B pruning
    private static int MMAB(Cell[][] node, Player player, int depth, boolean max, int alpha, int beta){
        nodesExplored++;
        Player oplayer = (player == Player.Black) ? Player.White : Player.Black;
        //if terminal reached or depth limit reached evaluate
        if(depth == 0 || Board.getPossibilities(node, player).isEmpty()){
            //BoardPrinter bpe = new BoardPrinter(node,"Depth : " + depth);
            return evaluateBoard(node, player, oplayer);
        }
        //if no moves available then forfeit turn
        if((max && !Board.getPossibilities(node, player).isEmpty()) || (!max && !Board.getPossibilities(node, oplayer).isEmpty())){
            //System.out.println("Forfeit State Reached !");
            return MMAB(node,player,depth-1,!max,alpha,beta);
        }
        int score;
        if(max){
            //maximizing
            score = Integer.MIN_VALUE;
            for(Cell move : Board.getPossibilities(node,player)){ //my turn
                //create new node
                Cell[][] newNode = Board.getNewBoardAfterMove(node,move,player);
                //recursive call
                int childScore = MMAB(newNode,player,depth-1,false,alpha,beta);
                if(childScore > score) score = childScore;
                //update alpha
                if(score > alpha) alpha = score;
                if(beta <= alpha) break; //Beta Cutoff
            }
        }else{
            //minimizing
            score = Integer.MAX_VALUE;
            for(Cell move : Board.getPossibilities(node,oplayer)){ //opponent turn
                //create new node
                Cell[][] newNode = Board.getNewBoardAfterMove(node,move,oplayer);
                //recursive call
                int childScore = MMAB(newNode,player,depth-1,true,alpha,beta);
                if(childScore < score) score = childScore;
                //update beta
                if(score < beta) beta = score;
                if(beta <= alpha) break; //Alpha Cutoff
            }
        }
        return score;
    }

    public static int evaluateBoard(Cell[][] cells, Player player, Player oppPlayer) {
        int score = 0;
        for (int r = 0; r < 8; ++r) {
            for (int c = 0; c < 8; ++c) {
                if (cells[r][c].getPlayer() == player)
                    score += BOARD_VALUE[r][c];
                else if (cells[r][c].getPlayer() == oppPlayer)
                    score -= BOARD_VALUE[r][c];
            }
        }
        return score;
    }

}