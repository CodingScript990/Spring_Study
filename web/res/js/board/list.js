const moveToDetail = (iboard) => {
    location.href = '/board/detail?iboard=' + iboard;
};

const setEvent = (tr) => {
    tr.addEventListener('click', () => {
        console.log(tr.dataset.iboard);
        moveToDetail(tr.dataset.iboard);
    });
}

let trList = document.querySelectorAll('.record'); // tr.record

// Hosting
for (let i = 0; i < trList.length; i++) {
    let tr = trList[i];
    setEvent(tr);
}


