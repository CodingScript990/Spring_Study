// 삭제버튼 누르면 "삭제하시겠습니까?" confirm창이 나타나고
// 긍정을 누르면 삭제 처리를 할 것이다.(주소이동만 하면된다.) /board/del?iboard=1
const btnContainerElem = document.querySelector('#btnContainer');

if (btnContainerElem) {
    const btnDelElem = btnContainerElem.querySelector('#btnDel');
    btnDelElem.addEventListener('click', () => {
        if (confirm('삭제 하시겠습니까?')) {
            // true(Yes)
            location.href = '/board/del?iboard=' + btnContainerElem.dataset.iboard;
        }
    });
}
