import {instance} from '../axios'

export default {
    //获取所有歌曲
    getAllSongs(){
        return instance.get('/api/getAllSongs');
    },
    //保存歌曲
    saveSong(data){
        return instance.post('/api/addSong', data);
    },

    //获取歌曲详细
    getSongDetail(id){
        return instance.post('/api/getSongDetail',id);
    },
    //删除歌曲
    deleteSong(id){
        return instance.post('/api/deleteSong', id);
    },
    //修改歌曲
    updateSong(data){
        return instance.post('/api/updateSong', data);
    }
}
