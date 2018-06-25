// 定义一个table内容的组件

Vue.component('table-content', {
  props: ['dataList'],
  template: '<div>{{dataList}}</div>'
})