import React from "react";

function WagonInfoPanel({train})
{
    const total_seats = train.get_all_seats();
    const occupied_seats = train.get_occupied_seats();
    const occupied_percentile = Math.round(occupied_seats/total_seats*100)
    return (
      <>
        <p><b>Train name:</b> {train.name}</p>
        <p><b>Locomotive model:</b> {train.locomotive.name}</p>
        <p><b>Locomotive speed:</b> {train.locomotive.speed}</p>
        <p><b>Locomotive production country:</b> {train.locomotive.country}</p>
        <p><b>Seats occupation:</b> {occupied_seats}/{total_seats}({occupied_percentile}%)</p>
      </>
    );
}

  
export default WagonInfoPanel;