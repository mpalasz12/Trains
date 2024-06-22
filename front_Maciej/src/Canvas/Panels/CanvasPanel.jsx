import React, { useEffect, useRef } from 'react';
import DrawUtils from './DrawUtils';

function CanvasPanel({stationsManager, connectionsManager, trainManager, current_train_index}) {
  const drawUtils = new DrawUtils();

  const drawMap = (ctx, img) => 
  {
    ctx.drawImage(img, 0, 0, 500, 500);
  }

  const drawStations = (ctx) => 
  {
    stationsManager.stations.forEach(station => drawUtils.drawStation(ctx, station.posX, station.posY, station.name));
  }

  const drawConnections = (ctx) => 
  {
    connectionsManager.connections.forEach(connection => drawUtils.drawLine(ctx, connection.city1.posX, connection.city1.posY, connection.city2.posX, connection.city2.posY))
  }

  const drawTrains = (ctx) =>
  {
    trainManager.trains.map((train, index) => drawUtils.drawTrain(ctx, train, index === current_train_index))
  }
  const animationFrameId = useRef(null);
  const canvasRef = useRef(null);
  useEffect(() => {
    const canvas = canvasRef.current;
    const ctx = canvas.getContext('2d');
    const img = new Image();
    img.src = process.env.PUBLIC_URL + 'mapa_polski.jpg';

    let lastTimestamp = performance.now();

    const drawOnCanvas = (timestamp) => {
      const deltaTime = timestamp - lastTimestamp;
      lastTimestamp = timestamp;

      trainManager.update_trains_positions(deltaTime)
      ctx.clearRect(0, 0, canvas.width, canvas.height);
      drawMap(ctx, img);
      drawConnections(ctx);
      drawStations(ctx);
      drawTrains(ctx);

      animationFrameId.current = requestAnimationFrame(drawOnCanvas);
    };

    img.onload = () => {
      animationFrameId.current = requestAnimationFrame(drawOnCanvas);
    };

    return () => {
      if (animationFrameId.current) {
        cancelAnimationFrame(animationFrameId.current);
      }
    };

  }, [stationsManager, connectionsManager, trainManager, current_train_index]);
;

  return <canvas ref={canvasRef} width={500} height={500} />;
}

export default CanvasPanel;