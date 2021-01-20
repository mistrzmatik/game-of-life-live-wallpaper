package pl.mateuszkraska.gameoflife_livewallpaper.render;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import pl.mateuszkraska.gameoflife_livewallpaper.game.Coordinates;

public class GridPainter {

    private final SurfaceHolder surfaceHolder;
    private final Paint paint;

    private int sizeOfSquare;
    private final int numberOfBlocksInWidth;
    private int backgroundColor;

    public GridPainter(SurfaceHolder surfaceHolder, int numberOfBlocksInWidth, int backgroundColor, int fieldColor){
        this.surfaceHolder = surfaceHolder;
        this.numberOfBlocksInWidth = numberOfBlocksInWidth;
        this.backgroundColor = backgroundColor;
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(fieldColor);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(sizeOfSquare);
    }

    public void setBackgroundColor(int color){
        backgroundColor = color;
    }

    public void setFieldColor(int color){
        paint.setColor(color);
    }

    private void drawField(Canvas canvas, int x , int y){
        canvas.drawPoint(x*sizeOfSquare,y*sizeOfSquare,paint);
    }

    public void draw( FieldState[][] mapOdStates ){

        Canvas canvas = null;
        try {
            canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                sizeOfSquare = canvas.getWidth()/numberOfBlocksInWidth;
                paint.setStrokeWidth(sizeOfSquare);
                canvas.drawColor(backgroundColor);
                for(int x = 0 ; x < mapOdStates.length ; x++){
                    for(int y = 0 ; y < mapOdStates[0].length ; y++){
                        if(mapOdStates[x][y].isActive()){
                            drawField(canvas,x,y);
                        }
                    }
                }
            }
        } finally {
            if (canvas != null)
                surfaceHolder.unlockCanvasAndPost(canvas);
        }

    }

    public Coordinates calculateFromInput(float x , float y){
        if(sizeOfSquare != 0){
            return new Coordinates((int)x/ sizeOfSquare,(int)y/ sizeOfSquare);
        }else{
            return new Coordinates(0,0);
        }
    }
}