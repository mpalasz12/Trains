function TrackInfoPanel({track}) 
{
    return (
      <>
        <p>Start station: <b>{track.getStartStation().name}</b></p>
        <p>End station: <b>{track.getEndStation().name}</b></p> 
        <br/>
        <p>Next station: <b>{track.getNextStation().name}</b></p>
      </>
    );
}

  
export default TrackInfoPanel;