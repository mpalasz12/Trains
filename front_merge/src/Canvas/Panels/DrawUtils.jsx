class DrawUtils
{
    
    drawStation(ctx, posx, posy, name){
        ctx.beginPath();
        ctx.arc(posx, posy, 5, 0, 2 * Math.PI);
        ctx.fillStyle = '#A2C5AC';
        ctx.strokeStyle = '#454851';
        ctx.lineWidth = 2;
        ctx.fill();
        ctx.stroke();
        ctx.font = '12px Arial';
        ctx.fillStyle = 'black';
        ctx.textAlign = 'center';
        ctx.fillText(name, posx, posy - 10);
    }

    drawTrain(ctx, train, isFocused){
        var posx = train.posX;
        var posy = train.posY;
        var name = train.name;
        var directionX = train.track.getNextStation().posX;
        var directionY = train.track.getNextStation().posY;
        const angle = Math.atan2(directionY - posy, directionX - posx);
        ctx.save();
        ctx.translate(posx, posy);
        ctx.rotate(angle);
      
        ctx.beginPath();
        ctx.fillStyle = isFocused ? "red" : "#D3D5D4";
        ctx.fillRect(-15, -5, 30, 10);
        ctx.strokeStyle = '#454851';
        ctx.lineWidth = 2;
        ctx.strokeRect(-15, -5, 30, 10);

        ctx.font = '10px Arial';
        ctx.fillStyle = 'black';
        ctx.textAlign = 'center';
        ctx.fillText(name, 0, 3);
        ctx.restore();
    }

    drawLine(ctx, startX, startY, endX, endY){
        ctx.beginPath();
        ctx.moveTo(startX, startY);
        ctx.lineTo(endX, endY);
        ctx.strokeStyle = '#D3D5D4';
        ctx.lineWidth = 5;
        ctx.stroke();
    }
}

export default DrawUtils;