$(document).ready(function () {
            // 변수를 선언합니다.
            var width = $('[data-role="slider"]').attr('data-width');
            var height = $('[data-role="slider"]').attr('data-height');
            var count = $('[data-role="slider"] div.item').length;

            // 스타일을 적용합니다.
            $('[data-role="slider"]').css({
                position: 'relative',
                overflow: 'hidden',
                width: width,
                height: height
            }).find('.container').css({
                position: 'absolute',
                width: count * width,
                overflow: 'hidden'
            }).find('.item').css({
                width: width,
                height: height,
                float: 'left'
            });
            // 변수를 선언합니다.
            var currentPage = 0;
            var changePage = function () {
                $('[data-role="slider"] > .container').animate({
                    left: -currentPage * width
                }, 500);
            };
            // 이벤트를 연결합니다.
            $('#left-button').click(function () {
                if (currentPage > 0) {
                    // 왼쪽으로 이동
                    currentPage = currentPage - 1;
                    changePage();
                }else{
                   currentPage = count - 1;
                    changePage();
                }
            });
            $('#right-button').click(function () {
                if (currentPage < count - 1) {
                    // 오른쪽으로 이동
                    currentPage = currentPage + 1;
                    changePage();
                }else{
                   currentPage = 1;
                   changePage();
                }
            });
        });