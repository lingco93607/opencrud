// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'


Vue.config.productionTip = false

var app = new Vue({
	el: '#table',
	data: {
		addDetail: {},
		editlist: false,
		editDetail: {},
		headInfo:[{text:'序号'},{text:'姓名'},{text:'语文'},{text:'数学'},{text:'英语'},{text:'操作'}],
		newsList: [{
			id:0,
			name: 'zyy',
			chinese: '80',
			math: '80',
			english: "80"
		}, {
			id:1,
			name: 'zyy',
			chinese: '80',
			math: '80',
			english: "80"
		}, {
			id:2,
			name: 'zyy',
			chinese: '80',
			math: '80',
			english: "80"
		}, {
			id:3,
			name: 'zyy',
			chinese: '80',
			math: '80',
			english: "80"
		}],
		editid:''
	},
	mounted() {

	},
	methods: {

		//新增
		adddetail() {
			//这里的思路应该是把this.addDetail传给服务端，然后加载列表this.newsList
			//this.newsList.push(this.addDetail)
			this.newsList.push({
				name: this.addDetail.name,
				chinese: this.addDetail.chinese,
				math: this.addDetail.math,
				english: this.addDetail.english,
			})

			//axios.post('url',this.addDetail).then((res) =>{
			//若返回正确结果，清空新增输入框的数据
			//this.addDetail.title = ""
			//this.addDetail.user = ""
			//this.addDetail.dates = ""
			//})

		},
		//删除
		deletelist(id, i) {
			this.newsList.splice(i, 1);
			//这边可以传id给服务端进行删除  ID = id
			//axios.get('url',{ID:id}).then((res) =>{
			//			加载列表				
			//})
		},
		//编辑
		edit(item) {
			this.editDetail = {			
				name: item.name,
				chinese: item.chinese,
				math: item.math,
				english: item.english,
			}
			this.editlist = true
			this.editid = item.id
			
		},
		//确认更新
		update() {
			//编辑的话，也是传id去服务端
			//axios.get('url',{ID:id}).then((res) =>{
			//			加载列表				
			//})
			let _this= this
			for(let i = 0; i < _this.newsList.length; i++) {
				if(_this.newsList[i].id ==this.editid) {
					_this.newsList[i] = {
						title: _this.editDetail.title,
						user: _this.editDetail.user,
						dates: _this.editDetail.dates,
						id: this.editid
					}
					this.editlist = false
				}
			}
		}
	}
})
