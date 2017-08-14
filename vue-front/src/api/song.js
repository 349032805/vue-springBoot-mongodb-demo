import {instance} from '../axios'

export default {
    //获取所有歌曲
    getAllSongs(){
        return instance.get('/api/getAllSongs');
    },
    //保存歌曲
    saveSong(data){
        return instance.post('/api/saveSong', data);
    },

    //获取歌曲详细
    getSongDetail(data){
        return instance.post('/api/getSongDetail', data);
    },
    //删除歌曲
    deleteSong(data){
        return instance.post('/api/deleteSong', data);
    },
    //修改歌曲
    updateSong(data){
        return instance.post('/api/updateSong', data);
    }
}
