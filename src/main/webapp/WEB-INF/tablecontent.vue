// 定义一个table内容的组件

Vue.component('table-content', {
  props: ['dataList'],
  template: `
      <tbody id="">
    	<tr v-for="(item, index) in dataList">
          <td width="5%">{{index+1}}</td>
          <td width="20%">{{item.tname}}</td>
          <td width="20%">{{item.sex}}</td>
          <td width="20%">{{item.major}}</td>
        </tr>
      </tbody>
  `
})