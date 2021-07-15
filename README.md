"# mylotto" 
2021-07-15 
[ LinearLayout ]
1. LinearLayout은 가로 또는 세로로 뷰를 순차적으로 배치한다.
2. LinearLayout의 배치 기준은 gravity, layout_gravity 속성을 이용하여 변경 가능하다.
3. gravity 속성은 뷰 자신의 컨텐츠 또는 자식 뷰들의 배치 기준을 변경한다.
4. layout_gravity 속성은 부모 뷰 안에서 뷰 자신의 배치 기준을 변경한다.

[RelativeLayout]
 - RelativeLayout은 LinearLayout에 비해 View의 중첩을 줄일 수 있다
 - LinearLayout을 익힐 때, View의 정렬 방향이 바뀌게 되면 LinearLayout 안에 또 LinearLayout을 넣어야하지만 RelativeLayout은 변경 가능하다.
 - 단점으론 View가 바뀌면 수정 해야 하는 것들이 많다.
