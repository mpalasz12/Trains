import React from "react";

function LocomotiveBtn({isFocused, handleLocomotiveClick}) {
  return (
    <>
    {isFocused ? (
        <button className="btn_locomotive_focused" onClick={() => handleLocomotiveClick()}>
        Info
        </button>
    ) : (
        <button className="btn_locomotive" onClick={() => handleLocomotiveClick()}>
        Info
        </button>
    )}
    </>
  );
}
  
export default LocomotiveBtn;