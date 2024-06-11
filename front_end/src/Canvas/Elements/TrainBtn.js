function TrainBtn({isFocused, handleTrainClick, train, index}) {
    return (
        <>
        {isFocused ? (
            <button className="btn_train_focused" onClick={() => handleTrainClick(index)}>
                {train.name}
            </button>
        ) : (
            <button className="btn_train" onClick={() => handleTrainClick(index)}>
                {train.name}
            </button>
        )}
        </>
    );
}

export default TrainBtn;