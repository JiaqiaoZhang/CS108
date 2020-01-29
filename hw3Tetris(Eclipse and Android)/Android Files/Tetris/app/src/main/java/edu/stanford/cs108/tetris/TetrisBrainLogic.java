package edu.stanford.cs108.tetris;

public class TetrisBrainLogic extends TetrisLogic {
    private boolean brainMode = true;
    private Brain.Move bestMove;
    private DefaultBrain defaultBrain;

    public TetrisBrainLogic(TetrisUIInterface uiInterface) {
        super(uiInterface);

    }

    @Override
    public void tick(int verb) {
        if (brainMode && verb == DOWN) {
            board.undo();

            bestMove = defaultBrain.bestMove(board, currentPiece, HEIGHT, bestMove);
            if(bestMove != null){
                if(!currentPiece.equals(bestMove.piece)){
                    tick(ROTATE);
                }
                if(bestMove.x < currentX){
                    super.tick(LEFT);
                }else if(bestMove.x > currentX){
                    super.tick(RIGHT);
                }
            }

        }
        super.tick(verb);
    }

    public void setBrainMode(boolean brainMode) {
        this.brainMode = brainMode;
        if (this.brainMode) {
            defaultBrain = new DefaultBrain();
            bestMove = new Brain.Move();
        }
    }
}