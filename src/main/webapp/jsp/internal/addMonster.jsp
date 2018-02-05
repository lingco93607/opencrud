<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="Access-Control-Allow-Origin" content="*">
	<title>内部添加野怪</title>

	<%@ include file = "../../jsp/internal/internalHead.jsp" %>
	
	<style type="text/css">
	tr {
		text-align: center;
		vertical-align: center;
	}
	textarea{ resize:none;}
	td {
		text-align: center;
		vertical-align: center;
	}
	.panel-addMonster {
		display: flex;
		justify-content: space-around;
		align-items: center;
	}


	
	</style>
</head>
<body ng-app="internet" ng-controller="showMonster" ng-init="getMonsterRequest()">
	<div class="panel panel-primary" >
		<div class="panel-heading">添加野怪</div>
		<div class="panel-addMonster">
	  	<div class="panel-body">
	  		<table class="table table-striped table-hover table-bordered">
	  			<tr>
		  			<td colspan="2">野怪名字</td>
		  			<td colspan="1"><input id="monster_name" type="text" class="form-control" placeholder="英雄名字" aria-describedby="basic-addon1" ng-model="monster_name"></td>
		  			<td colspan="1">类型</td>
		  			<td colspan="2"><input id="monster_types" type="text" class="form-control" placeholder="类型" aria-describedby="basic-addon1" ng-model="monster_types"></td>
		  		</tr>
		  		<tr>
		  			<td colspan="2">等级</td>
		  			<td colspan="1">
		  				<div class="input-group spinner" data-trigger="spinner" >
							<div class="input-group">
							    <span class="input-group-addon"><a href="javascript:;" class="spin-up" data-spin="up"><i class="glyphicon glyphicon-plus"></i></a></span>
							    <input id="monster_level"  type="text" class="form-control" value="0" data-max="999" data-min="0" data-step="1" placeholder="等级">
							    <span class="input-group-addon"><a href="javascript:;" class="spin-down" data-spin="down"><i class="glyphicon glyphicon-minus"></i></a></span>
							</div>
						</div>

		  			</td>
		  			<td colspan="1">境界</td>
		  			<td colspan="1">
		  				<div class="input-group spinner" data-trigger="spinner" >
							<div class="input-group">
							    <span class="input-group-addon"><a href="javascript:;" class="spin-up" data-spin="up"><i class="glyphicon glyphicon-plus"></i></a></span>
							    <input id="monster_realm"  type="text" class="form-control" value="0" data-max="999" data-min="0" data-step="1" placeholder="等级" >
							    <span class="input-group-addon"><a href="javascript:;" class="spin-down" data-spin="down"><i class="glyphicon glyphicon-minus"></i></a></span>
							</div>
						</div>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td colspan="2">魅力</td>
		  			<td colspan="1">
		  				<div class="input-group spinner" data-trigger="spinner">
							<div class="input-group">
							    <span class="input-group-addon"><a href="javascript:;" class="spin-up" data-spin="up"><i class="glyphicon glyphicon-plus"></i></a></span>
							    <input id="monster_charm" type="text" class="form-control" value="0" data-max="999" data-min="0" data-step="1" placeholder="等级" >
							    <span class="input-group-addon"><a href="javascript:;" class="spin-down" data-spin="down"><i class="glyphicon glyphicon-minus"></i></a></span>
							</div>
						</div>
		  			</td>
		  			<td colspan="1">攻击力</td>
		  			<td colspan="1">
		  				<div class="input-group spinner" data-trigger="spinner" >
							<div class="input-group">
							    <span class="input-group-addon"><a href="javascript:;" class="spin-up" data-spin="up"><i class="glyphicon glyphicon-plus"></i></a></span>
							    <input id="monster_attack" type="text" class="form-control" value="0" data-max="999" data-min="0" data-step="1" placeholder="等级" >
							    <span class="input-group-addon"><a href="javascript:;" class="spin-down" data-spin="down"><i class="glyphicon glyphicon-minus"></i></a></span>
							</div>
						</div>
		  			</td>
		  		</tr>

		  		<tr>
		  			<td colspan="2">防御力</td>
		  			<td colspan="1">
		  				<div class="input-group spinner" data-trigger="spinner" >
							<div class="input-group">
							    <span class="input-group-addon"><a href="javascript:;" class="spin-up" data-spin="up"><i class="glyphicon glyphicon-plus"></i></a></span>
							    <input id="monster_defence" type="text" class="form-control" value="0" data-max="999" data-min="0" data-step="1" placeholder="等级" >
							    <span class="input-group-addon"><a href="javascript:;" class="spin-down" data-spin="down"><i class="glyphicon glyphicon-minus"></i></a></span>
							</div>
						</div>
		  			</td>
		  			<td colspan="1">速度</td>
		  			<td colspan="1">
		  				<div class="input-group spinner" data-trigger="spinner" >
							<div class="input-group">
							    <span class="input-group-addon"><a href="javascript:;" class="spin-up" data-spin="up"><i class="glyphicon glyphicon-plus"></i></a></span>
							    <input id="monster_speed" id="a" type="text" class="form-control" value="0" data-max="999" data-min="0" data-step="1" placeholder="等级" >
							    <span class="input-group-addon"><a href="javascript:;" class="spin-down" data-spin="down"><i class="glyphicon glyphicon-minus"></i></a></span>
							</div>
						</div>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td rowspan="3">五行属性</td>
		  			<td>金</td>
		  			<td>
		  				<div class="input-group spinner" data-trigger="spinner" >
							<div class="input-group">
							    <span class="input-group-addon"><a href="javascript:;" class="spin-up" data-spin="up"><i class="glyphicon glyphicon-plus"></i></a></span>
							    <input id="monster_jin"  type="text" class="form-control" value="0" data-max="999" data-min="0" data-step="1" placeholder="等级" >
							    <span class="input-group-addon"><a href="javascript:;" class="spin-down" data-spin="down"><i class="glyphicon glyphicon-minus"></i></a></span>
							</div>
						</div>
		  			</td>
		  			<td>木</td>
		  			<td>
		  				<div class="input-group spinner" data-trigger="spinner" >
							<div class="input-group">
							    <span class="input-group-addon"><a href="javascript:;" class="spin-up" data-spin="up"><i class="glyphicon glyphicon-plus"></i></a></span>
							    <input id="monster_mu"  type="text" class="form-control" value="0" data-max="999" data-min="0" data-step="1" placeholder="等级" >
							    <span class="input-group-addon"><a href="javascript:;" class="spin-down" data-spin="down"><i class="glyphicon glyphicon-minus"></i></a></span>
							</div>
						</div>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>水</td>
		  			<td>
		  				<div class="input-group spinner" data-trigger="spinner" >
							<div class="input-group">
							    <span class="input-group-addon"><a href="javascript:;" class="spin-up" data-spin="up"><i class="glyphicon glyphicon-plus"></i></a></span>
							    <input id="monster_shui"  type="text" class="form-control" value="0" data-max="999" data-min="0" data-step="1" placeholder="等级" >
							    <span class="input-group-addon"><a href="javascript:;" class="spin-down" data-spin="down"><i class="glyphicon glyphicon-minus"></i></a></span>
							</div>
						</div>
		  			</td>
		  			<td>火</td>
		  			<td>
		  				<div class="input-group spinner" data-trigger="spinner" >
							<div class="input-group">
							    <span class="input-group-addon"><a href="javascript:;" class="spin-up" data-spin="up"><i class="glyphicon glyphicon-plus"></i></a></span>
							    <input id="monster_huo"  type="text" class="form-control" value="0" data-max="999" data-min="0" data-step="1" placeholder="等级" >
							    <span class="input-group-addon"><a href="javascript:;" class="spin-down" data-spin="down"><i class="glyphicon glyphicon-minus"></i></a></span>
							</div>
						</div>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>土</td>
		  			<td>
		  				<div class="input-group spinner" data-trigger="spinner" >
							<div class="input-group">
							    <span class="input-group-addon"><a href="javascript:;" class="spin-up" data-spin="up"><i class="glyphicon glyphicon-plus"></i></a></span>
							    <input id="monster_tu"  type="text" class="form-control" value="0" data-max="999" data-min="0" data-step="1" placeholder="等级" >
							    <span class="input-group-addon"><a href="javascript:;" class="spin-down" data-spin="down"><i class="glyphicon glyphicon-minus"></i></a></span>
							</div>
						</div>
		  			</td>
		  			<td></td>
		  			<td></td>
		  		</tr>
		  		<tr>
		  			<td rowspan="4">技能</td>
		  			<td>技能名字</td>
		  			<td>
		  				<input id="monster_skill_name" type="text" class="form-control" placeholder="技能名字" aria-describedby="basic-addon1" ng-model="monster_skill_name">
		  			</td>
		  			<td rowspan="2">技能介绍</td>
		  			<td rowspan="2">
		  				<div class="form-group">
							<textarea id="monster_skill_introduction" class="form-control" rows="3" ng-model="monster_skill_introduction"></textarea>
						</div>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>技能伤害</td>
		  			<td>
		  				<div class="input-group spinner" data-trigger="spinner" >
							<div class="input-group">
							    <span class="input-group-addon"><a href="javascript:;" class="spin-up" data-spin="up"><i class="glyphicon glyphicon-plus"></i></a></span>
							    <input id="monster_skill_damage"  type="text" class="form-control" value="0" data-max="999" data-min="0" data-step="1" placeholder="技能伤害" >
							    <span class="input-group-addon"><a href="javascript:;" class="spin-down" data-spin="down"><i class="glyphicon glyphicon-minus"></i></a></span>
							</div>
						</div>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>眩晕时间</td>
		  			<td>
		  				<input id="monster_skill_dizzy" type="text" class="form-control" placeholder="Double 类型" aria-describedby="basic-addon1" ng-model="monster_skill_dizzy">
		  			</td>
		  			<td>眩晕范围</td>
		  			<td>
		  				<div class="input-group spinner" data-trigger="spinner" >
							<div class="input-group">
							    <span class="input-group-addon"><a href="javascript:;" class="spin-up" data-spin="up"><i class="glyphicon glyphicon-plus"></i></a></span>
							    <input id="monster_skill_dizzyDistance"  type="text" class="form-control" value="0" data-max="999" data-min="0" data-step="1" placeholder="等级" >
							    <span class="input-group-addon"><a href="javascript:;" class="spin-down" data-spin="down"><i class="glyphicon glyphicon-minus"></i></a></span>
							</div>
						</div>
		  			</td>
		  			
		  		</tr>
		  		<tr>
		  			<td>技能作用的距离</td>
		  			<td>
		  				<input id="monster_skill_distance" type="text" class="form-control" placeholder="Double 类型" aria-describedby="basic-addon1" ng-model="monster_skill_distance">
		  			</td>

		  			<td>技能恢复时间</td>
		  			<td>
		  				<div class="input-group spinner" data-trigger="spinner" >
							<div class="input-group">
							    <span class="input-group-addon"><a href="javascript:;" class="spin-up" data-spin="up"><i class="glyphicon glyphicon-plus"></i></a></span>
							    <input id="monster_skill_recoveryTime"  type="text" class="form-control" value="0" data-max="999" data-min="0" data-step="1" placeholder="等级" >
							    <span class="input-group-addon"><a href="javascript:;" class="spin-down" data-spin="down"><i class="glyphicon glyphicon-minus"></i></a></span>
							</div>
						</div>
		  			</td>
		  			
		  		</tr>

	  		</table>
	  		<center>
	  			<button type="button" class="btn btn-primary" ng-click="addMonster()">添加</button>
	  		</center>
	  	</div>
	  	<!-- <div class="panel-body">
	  		<table class="table" id="resultMonster">
	  			<tr>
		  			<td>
		  				<img src="../../action/article/img/timg/jpg" class="img-responsive" >
		  			</td>
		  			<td>
		  				<div id="chart" style="width: 300px; height: 300px;"></div>
		  				<table class="table table-hove">
		  					<tr>
		  						<td>金</td>
		  						<td></td>
		  					</tr>
		  					<tr>
		  						<td>木</td>
		  						<td></td>
		  					</tr>
		  					<tr>
		  						<td>水</td>
		  						<td></td>
		  					</tr>
		  					<tr>
		  						<td>火</td>
		  						<td></td>
		  					</tr>
		  					<tr>
		  						<td>土</td>
		  						<td></td>
		  					</tr>

		  				</table>
		  			</td>
		  		</tr>
		  		<tr>
		  			<td>
		  				<table class="table table-striped table-hover">
		  					<tr>
		  						<td >英雄名字</td>
		  						<td>{{monstername}}</td>
								<td >类型</td>
								<td>{{types}}</td>
		  					</tr>
		  					<tr>
		  						<td >等级</td>
		  						<td><p ng-bind="monsterLevel"></p></td>
								<td >境界</td>
								<td></td>
		  					</tr>
							<tr>
								<td >魅力</td>
								<td></td>
								<td >攻击力</td>
								<td></td>
							</tr>
							<tr>
								<td >防御力</td>
								<td></td>
								<td >速度</td>
								<td></td>
							</tr>
							
		  				</table>
		  			</td>
		  			<td>
		  				<table class="table table-striped table-hover">
		  					<tr>
		  						<td>技能名字</td>
		  						<td></td>
		  						<td colspan="5">技能介绍</td>
		  						<td></td>
		  					</tr>
		  					<tr>
		  						<td>技能伤害</td>
		  						<td></td>
		  					</tr>
		  					<tr>
		  						<td>眩晕时间</td>
		  						<td></td>
		  					</tr>
		  					<tr>
		  						<td>眩晕范围</td>
		  						<td></td>
		  					</tr>
		  					<tr>
		  						<td>技能作用的距离</td>
		  						<td></td>
		  					</tr>
		  					<tr>
		  						<td>技能恢复时间</td>
		  						<td></td>
		  					</tr>
		  				</table>
		  			</td>
		  		</tr>
	  		</table>
	  		
	  	</div> -->
	  	
	  	</div>
	</div>
	<div class="panel panel-success">
	  <div class="panel-heading">野怪列表</div>
	  <div class="panel-body">
	    <div class="table-responsive" >
			<table class="table table-striped table-hover table-bordered">
				<thead>
					<tr>
						<td rowspan="3">序列ID</td>
						<td rowspan="2">英雄名字</td>
							
						<td rowspan="2">类型</td>
						<td rowspan="2">等级</td>

						<td rowspan="2">境界</td>
						<td rowspan="2">魅力</td>
						<td rowspan="2">攻击力</td>
						<td rowspan="2">防御力</td>
						<td rowspan="2">速度</td>
						<td colspan="5">五行属性</td>
					</tr>
					<tr>
						
						<td>金</td>
						<td>木</td>
						<td>水</td>
						<td>火</td>
						<td>土</td>
						<!-- <td>五行属性</td>
						<td>技能</td> -->
					</tr>
					<tr style="font-size: 12px;">
						<td rowspan="1">名字</td>
						<td colspan="7">技能介绍</td>
						<td>技能伤害</td>
						<td>眩晕时间</td>
						<td>眩晕范围</td>
						<td>技能作用的距离</td>
						<td>技能恢复时间</td>
						<!-- <td>五行属性</td>
						<td>技能</td> -->
					</tr>
				</thead>
				<!-- <tbody>
					<tr ng-repeat="monster in monsters">
						<td rowspan="2">{{monster.monsterID}}</td>
						<td>{{monster.name}}</td>
						<td>{{monster.types}}</td>
						<td>{{monster.level}}</td>

						<td>{{monster.realm}}</td>
						<td>{{monster.charm}}</td>
						<td>{{monster.attack}}</td>
						<td>{{monster.defence}}</td>
						<td>{{monster.speed}}</td>
						<td>{{monster.jin}}</td>
						<td>{{monster.mu}}</td>
						<td>{{monster.shui}}</td>
						<td>{{monster.huo}}</td>
						<td>{{monster.tu}}</td>

						<tr>
							<td>{{monster.name}}</td>
							<td colspan="7">{{monster.introduction}}</td>
							<td> 技能伤害: {{monster.damage}}</td>
							<td> 眩晕时间: {{monster.dizzy}}</td>
							<td> 眩晕范围: {{monster.dizzyDistance}}</td>
							<td> 技能作用的距离: {{monster.distance}}</td>
							<td> 技能恢复时间: {{monster.recoveryTime}}</td>
						</tr>

					</tr>	
					<!-- <tr ng-repeat="monsterSkill in monsterSkills">
						
					</tr> 
				</tbody> -->
				 <tbody ng-repeat="monster in monsters">
			        <tr ng-repeat="test in monster.test track by $index">
			            <td ng-if="$index ==0" rowspan={{monster.test.length}}>{{monster.monsterID}}</td>
			            <td >{{test.name}}</td>
						<td ng-if="$index ==0">{{test.types}}</td>
						<td ng-if="$index ==0">{{test.level}}</td>

						<td ng-if="$index ==0">{{test.realm}}</td>
						<td ng-if="$index ==0">{{test.charm}}</td>
						<td ng-if="$index ==0">{{test.attack}}</td>
						<td ng-if="$index ==0">{{test.defence}}</td>
						<td ng-if="$index ==0">{{test.speed}}</td>
						<td ng-if="$index ==0">{{test.jin}}</td>
						<td ng-if="$index ==0">{{test.mu}}</td>
						<td ng-if="$index ==0">{{test.shui}}</td>
						<td ng-if="$index ==0">{{test.huo}}</td>
						<td ng-if="$index ==0">{{test.tu}}</td>

						<td ng-if="$index ==1" colspan="7">{{test.introduction}}</td>
						<td ng-if="$index ==1">{{test.damage}}</td>
						<td ng-if="$index ==1">{{test.dizzy}}</td>
						<td ng-if="$index ==1">{{test.dizzyDistance}}</td>
						<td ng-if="$index ==1">{{test.distance}}</td>
						<td ng-if="$index ==1">{{test.recoveryTime}}</td>
			        </tr>
			    </tbody>

			</table>
		</div>

	  </div>
	</div>
	

	<script>
		var start = 0;
    	angular.module('internet', [])

		.controller('showMonster', function($scope, $http) {
			$scope.monsters = [];
			$scope.monsterSkills = [];

			$scope.getMonsterRequest = function() {
				$http({
					method: 'GET',
					//url: 'http://106.15.193.89:8080/zhazha/action/monster/getmonsters?start=0&end=5',
					url: window.location.protocol + '//' + window.location.host + '/zhazha/action/monster/getmonsters?start=0&end=50',
				}).then(function successCallback(response) {
					if(200 == response.status) {
						$scope.monsters = [];
						var monstersInfo = response.data;
						var length = monstersInfo.length;
						for (var i=0; i<length; i++) {
							var skills = [];
							//var skillsLength = monstersInfo[i].skill.length;

							// for (var j=0; j<1; j++) {
							// 	skills.push({
							// 		id: monstersInfo[i].skill.id,
							// 		name: monstersInfo[i].skill.name,
							// 		introduction: monstersInfo[i].skill.introduction,
							// 		damage: monstersInfo[i].skill.damage,
							// 		dizzy: monstersInfo[i].skill.dizzy,
							// 		dizzyDistance: monstersInfo[i].skill.dizzyDistance,
							// 		distance: monstersInfo[i].skill.distance,
							// 		recoveryTime: monstersInfo[i].skill.recoveryTime,
							// 	})
							// }
							$scope.monsters.push({
								monsterID: monstersInfo[i].monsterID,
								test : [{ 
										name: monstersInfo[i].name,  //名字
										types: monstersInfo[i].types,   //类型
										level: monstersInfo[i].level,  //等级
										realm: monstersInfo[i].realm, //境界
										charm: monstersInfo[i].charm,  //魅力值
										attack: monstersInfo[i].attack,  //攻击力
										defence: monstersInfo[i].defence, // 防御力
										speed: monstersInfo[i].speed, // 速度
										jin: monstersInfo[i].wuxing.jin,
										mu: monstersInfo[i].wuxing.mu,
										shui: monstersInfo[i].wuxing.shui,
										huo: monstersInfo[i].wuxing.huo,
										tu: monstersInfo[i].wuxing.tu,
									},
									{	id: monstersInfo[i].skill.id,
								 		name: monstersInfo[i].skill.name,
										introduction: monstersInfo[i].skill.introduction,
										damage: monstersInfo[i].skill.damage,
										dizzy: monstersInfo[i].skill.dizzy,
										dizzyDistance: monstersInfo[i].skill.dizzyDistance,
										distance: monstersInfo[i].skill.distance,
										recoveryTime: monstersInfo[i].skill.recoveryTime,
									},
								]
								
								
								
							})

							$scope.monsterSkills.push({
								id: monstersInfo[i].skill.id,
							 	name: monstersInfo[i].skill.name,
								introduction: monstersInfo[i].skill.introduction,
								damage: monstersInfo[i].skill.damage,
								dizzy: monstersInfo[i].skill.dizzy,
								dizzyDistance: monstersInfo[i].skill.dizzyDistance,
								distance: monstersInfo[i].skill.distance,
								recoveryTime: monstersInfo[i].skill.recoveryTime,
							})
						}
					} else {
						alert("服务器返回错误，添加失败");
					}
				}, function errorCallback(response) {
					// 请求失败执行代码
					alert("请求失败");
				})
				// $.ajax({
				//     url:"http://106.15.193.89:8080/zhazha/action/monster/getmonsters?start=0&end=5",
				//     dataType:'jsonp',
				//     async:false,
				//     processData: false, 
				//     type:'get',
				//     success:function(data, XMLHttpRequest, textStatus){
				//     	console.log(data);
				//     	console.log (XMLHttpRequest.status);
				//        	console.log (XMLHttpRequest.readyState);
				//        	console.log (textStatus);
				//     },
				//     error:function(xhr,textStatus){
    //     				console.log('错误');
    //     				console.log(xhr);
    //     				console.log(textStatus);
				// 	}
				// });
			}

			$scope.addMonster = function() {

				$scope.monster_name = $.trim($("#monster_name").val());
				$scope.monster_types = $.trim($("#monster_types").val());
				$scope.monster_skill_name = $.trim($("#monster_skill_name").val());
				$scope.monster_skill_introduction = $.trim($("#monster_skill_introduction").val());
				$scope.monster_skill_dizzy = $.trim($("#monster_skill_dizzy").val());
				$scope.monster_skill_distance = $.trim($("#monster_skill_distance").val());
				

				if( $scope.monster_name == "" || $scope.monster_types == "" ||  $scope.monster_skill_name == "" || $scope.monster_skill_introduction == "" || $scope.monster_skill_distance  == "" || $scope.monster_skill_dizzy == "" ) {
					alert("信息填写不完整")
				} else {

					if(isDoubleRgexp($scope.monster_skill_dizzy) && isDoubleRgexp($scope.monster_skill_dizzy)) {
						var monsterMap = {
							"monstername": $scope.monster_name,
							"monstertype": $scope.monster_types,
							"level": Number($("#monster_level").val()),
							"Realm": Number($("#monster_realm").val()),
							"charm": Number($("#monster_charm").val()),
							"attack": Number($("#monster_attack").val()),
							"defence": Number($("#monster_defence").val()),
							"speed": Number($("#monster_speed").val()),
							"Jin": Number($("#monster_jin").val()),
							"Mu": Number($("#monster_mu").val()),
							"Shui": Number($("#monster_shui").val()),
							"Huo": Number($("#monster_huo").val()),
							"Tu": Number($("#monster_tu").val()),
							"skills":
								[{
									"name":$scope.monster_skill_name,
									"introduction": $scope.monster_skill_introduction,
									"damage": Number($("#monster_skill_damage").val()),
									"dizzy": $scope.monster_skill_distance,
									"dizzyDistance": Number($("#monster_skill_dizzyDistance").val()),
									"distance": $scope.monster_skill_distance,
									"recoveryTime": Number($("#monster_skill_recoveryTime").val())
								}]
						}
						$http({
							method: 'POST',
							url: window.location.protocol + '//' + window.location.host + '/zhazha/action/monster/createMonster',
							headers: {'Content-Type': 'application/json'},
							data: monsterMap
							//url: '../../action/article/getArticles?start=20&end=5&useruuid=1'
						}).then(function successCallback(response) {
								if(200 == response.status) {
									//console.log(response.data);
									alert(response.data.message);
									$scope.getMonsterRequest();
								}
								
						}, function errorCallback(response) {
							// 请求失败执行代码
						})

					} else {
						alert("眩晕时间/技能作用的距离 为Double类型")
					}
					
				}
			}


		});

		function echarsInitTest() {
			var myChart = echarts.init(document.getElementById("chart"));
			option = {
			    title: {
			        text: '五行属性'
			    },
			    legend: { enabled: true }, // 隐藏图例
			    tooltip: { trigger: 'axis'},
			    legend: {
			        data: ['野怪']
			    },
			    radar: {
			        // shape: 'circle',
			        name: {
			            textStyle: {
			                color: '#fff',
			                backgroundColor: '#999',
			                borderRadius: 3,
			                padding: [3, 5]
			           }
			        },
			        indicator: [
			           { name: '金', max: 100},
			           { name: '木', max: 100},
			           { name: '水', max: 100},
			           { name: '火', max: 100},
			           { name: '土', max: 100}
			        ]
			    },
			    series: [{
			        name: '五行属性',
			        type: 'radar',
			        // areaStyle: {normal: {}},
			        data : [
			            {
			                value : [55, 33, 22, 11, 77, 99],
			                name : '野怪'
			            }
			        ]
			    }]
			};

		myChart.setOption(option);
	}

		

		
	function formatDateTime(timeStamp) {
		var date = new Date(timeStamp);
	    var y = date.getFullYear();
	    var m = date.getMonth() + 1;
	    m = m < 10 ? ('0' + m) : m;
	    var d = date.getDate();
	    d = d < 10 ? ('0' + d) : d;
	    var h = date.gethours();
	    h = h < 10 ? ('0' + h) : h;
	    var minute = date.getMinutes();
	    var second = date.getSeconds();
	    minute = minute < 10 ? ('0' + minute) : minute;
	    second = second < 10 ? ('0' + second) : second;
	    return y + '-' + m + '-' + d + ' ' + h + ':' + minute + ':' + second;
	}


	//判断是否为空对象
	function isEmptyObject(obj) {   
	　　for (var key in obj){
	　　　　return false;//返回false，不为空对象
	　　}　　
	　　return true;//返回true，为空对象
	}

	//判断是否是否为浮点数	
	function isDoubleRgexp(str) {
		for(i=0;i<str.length;i++)  {
	    	if((str.charAt(i)<"0" || str.charAt(i)>"9")&& str.charAt(i) != '.'){
	      		return false;
	     	}
	  	}
	  	return true;
	}

	</script>


</body>
</html>