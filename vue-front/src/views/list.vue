<template>
  <div class="list">
  	<div class="title">我最喜欢的歌曲</div>
 	<table class="song-table">
 		<thead>
 			<th>歌曲</th>
 			<th>歌手</th>
 			<th>创建时间</th>
 			<th>更新时间</th>
 			<th>操作</th>
 		</thead>
 		<tbody>

 			<tr v-for="song of songs">
 				<td>{{song.songName}}</td>
 				<td>{{song.singer}}</td>
 				<td>{{song.createAt | formatDate}}</td>
 				<td>{{song.updateAt | formatDate}}</td>
 				<td>
 					<button type="button" class="btn btn-info" @click="showDetail(song)">查看</button>
 					<button type="button" class="btn btn-info" @click="edit(song)">修改</button>
 					<button type="button" class="btn btn-danger" @click="deleteSong(song)">删除</button>
 				</td>
 			</tr>
 		</tbody>
 	</table>

 	<div class="add-sign">
 		<span class='glyphicon glyphicon-plus-sign' @click="addSong()"></span>
 	</div>

 	 <!-- 添加/修改歌曲模态框 -->
 	 <div class="bg-layer" v-show="showModal"></div>
 	 <div class="add-modal"  v-show="showModal">
 	 	<div class="modal-title">{{mode==0?'添加':'修改'}}歌曲</div>
 	 	<form class="form-group form-horizontal song-form" role="form">
			<div class="form-group">
			    <label class="col-xs-3 col-md-3 control-label">歌曲名:</label>
			    <div class="col-xs-7 col-md-7">
			      <input class="form-control" type="text" placeholder="歌曲名" maxlength="20" v-model="songName">
			    </div>
			    <span class="col-xs-1 col-md-1 require">*</span>
			</div>

			<div class="form-group">
			    <label class="col-xs-3 col-md-3 control-label">歌手:</label>
			    <div class="col-xs-7 col-md-7">
			      <input class="form-control" type="text" placeholder="歌手" maxlength="4" v-model="singer">
			    </div>
			</div>
	  	</form>

	  	<div class="opt">
	  		<button type="button" class="btn btn-default float-left" @click="closeModal()">取消</button>
	  		<button type="button" class="btn btn-success float-right" @click="sureAddOrEdit()">确定</button>
	  	</div>

 	 </div>
  </div>
</template>

<script type="text/ecmascript-6">
	 import {formatDate} from '../utils/date.js';
	 import api from '../api/song.js';
     export default {
      data() {
          return {
          	mode: 0,
          	songId: "",
          	songName: "",
          	singer: "",
          	songs: [],
			showModal: false,
			song:{}
          }
        },
      created() {
          console.log("---------create");
          this._getSongs();
       },
      methods: {
      	addSong(){
      		this.showModal = true;
      		this.mode = 0;
      	},
      	closeModal(){
      		this.showModal = false;
      		this.songName = "";
      		this.singer = "";
      	},
      	sureAddOrEdit(){
			if(this.mode == 0){
				this._sureAdd();
			}else{
				this._sureEdit();
			}
      	},
          showDetail(song){
			let songId = song.id;
			console.log("前端songId:"+songId);
          	this.$router.push({name:'detail',params:{songId:songId}});
          },
          edit(song){
          	this.showModal = true;
          	this.mode = 1;
			this.songId = song.id;
          	this.songName = song.songName;
          	this.singer = song.singer;
          },
          deleteSong(song){
          	let id = song.id;
            api.deleteSong(id).then(response => {
				this.$message({
					type: 'success',
					message: '删除成功'
				});
                this._getSongs();
              }).catch((err) => {
                console.log(err);
            })
          },
          _getSongs(){
			api.getAllSongs().then((response) => {
				this.songs = response.data;
			});
          },
          _sureAdd(){
          	if(this.songName == ""){
          	 	alert("请输入歌名!");
          	 	return;
			}
			let song = {};
			song.songName = this.songName;
			song.singer = this.singer;
			song.createAt = new Date().getTime();
			song.updateAt = new Date().getTime();

			console.log("表单的song")
			console.log(song)
			console.log("表单的song")
			api.saveSong(song).then(({ data }) => { 
				if(data.success){
					this.$message({
						type: 'success',
						message: '保存成功'
					});
					this.songName  = "";
	          		this.singer = "";
					this.showModal = false;
					this._getSongs();
				}
			});

          },
          _sureEdit(){
          	if(this.songName == ""){
          	 	alert("请输入歌名!");
          	 	return;
			}

			let song = {};
			song.id = this.songId;
			song.songName = this.songName;
			song.singer = this.singer;
			song.updateAt = new Date().getTime();
			   
			api.updateSong(song).then(({ data }) => { 
				if(data.success){
					this.$message({
						type: 'success',
						message: '修改成功'
					});
					this.songName  = "";
	          		this.singer = "";
					this.showModal = false;
					this._getSongs();
				}
			});

          }
      },
      filters: {
	      formatDate(time) {
	        let date = new Date(time);
	        return formatDate(date, 'yyyy-MM-dd hh:mm:ss');
	      }
      }
    };
</script>

<style lang="less" scoped>
	.list {
		padding: 30px 0;
		.title {
			text-align: center;
			font-size: 20px;
		}

		.song-table {
			width: 100%;
			margin-top:20px;
			thead,tr{
				display: flex;
			}
		
			th,td{
				text-align: center;
				flex: 1;
				line-height: 50px;
			}

			th {
				border-bottom: 1px solid #e5e5e5;
			}

			td {
				border-bottom: 1px solid #66caf1;
			}
		}

		.add-sign {
			margin-top: 40px;
			text-align: center;
			font-size: 40px;
			color: #66caf1;
			span {
				cursor: pointer;
			}
		}

		.bg-layer {
		  position: fixed;
		  top: 0;
		  left: 0;
		  width: 100%;
		  height: 100%;
		  background: rgba(0,0,0,.5);
		  z-index: 10;
		}
		
		.add-modal {
			position: fixed;
			top: 0;
			left: 0;
			right: 0;
			bottom: 0;
			margin: auto;
			z-index: 1000;
			background: #fff;
			border-radius: 3px;
			width: 400px;
			height: 250px;
			overflow: hidden;

			.modal-title {
				width: 100%;
				height: 40px;
				line-height: 40px;
				text-align: center;
				font-size: 18px;
				font-weight: bold;
				border-bottom: 1px dashed #66caf1;
			}

			.song-form {
				margin-top: 20px;
				.require {
					color: red;
					text-align: left;
					line-height: 34px;
					padding:0;
					vertical-align: middle;
					font-size: 22px;
				}
			}

			.opt {
				text-align: center;
				margin-top: 40px;
				padding: 0 80px;
				button {
					padding-left: 26px;
					padding-right: 26px;
				}
			}

			.float-left {
				float: left;
			}

			.float-right {
				float: right;
			}
		}


	}
</style>
