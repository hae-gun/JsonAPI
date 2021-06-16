function response(room, msg, sender, isGroupChat, replier, ImageDB, packageName) {
    var answer = chkCode(msg);
    // Log.d(answer,true);
    if(answer != ''){
        replier.reply(answer);
    }
}
var tierSet = ['브론즈','실버','골드','플래티넘','플레티넘','다이아','루비','브','실','골','플','플레','플래','다','루'];
var transfer = {브론즈:'bronze',실버:'silver',골드:'gold',플래티넘:'platinum',플레티넘:'platinum',다이아:'diamond',루비:'ruby',
                브:'bronze',실:'silver',골:'gold',다:'diamond',루:'ruby',플:'platinum',플래:'platinum',플레:'platinum'};
var typeCode = {DP:25}
var typeSet = ['DP']
var chkCode = function(msg){
    var code;
    var level='';
    if(msg.includes('/')){
        code = msg.slice(1,msg.length);
        //  Log.d(code,true);
    }else{
        return '';
    }
    for(word of tierSet){
        if(code.startsWith(word) && (code.length-word.length<=1)){
            code=word+' '+code.substring(word.length);
            break;
        }
    }

    if(code.split(' ').length>1){
        level = code.split(' ')[1];
        code = code.split(' ')[0];
    }
    var regexp = /^[0-9]*$/
    if(level > 5 || !regexp.test(level)){
        return '';
    }
    // if(code.includes('안녕')){
    //     return '네, 안녕하세요.';
    // }

    var answer;
    // if(tierSet.includes(code)){
    //     code='설정';
    // }
    
    if(tierSet.includes(code)){
        var probJson = ajax(transfer[code],level);
        //var tmp = JSON.parse(probJson);
        // Log.d(probJson,true)
        answer = probJson.replace(/(<([^>]+)>)/g,"");
        // Log.d(answer,true)
        answer = JSON.parse(JSON.stringify(answer));
        var tmp = answer.split('},{');
        var result=[];
        answer = '랜덤 문제\n';
        for(data of tmp){
            data = data.replace(/\[\{/g,'').replace(/\}\]/g,'');
            // Log.d(data,true)
            var dtmp = data.split(',');
            var types = ''
            for(i in dtmp){
                if(i < 4 ){
                    var ddtmp = dtmp[i].split(':');
                    // Log.d(ddtmp,true)
                    ddtmp[1] = ddtmp[1].split('"')[1];
                    if(ddtmp[0]=='"level"'){
                        answer += ddtmp[1];
                        answer += ' :';
                    }else if(ddtmp[0]=='"name"'){
                        answer += ddtmp[1];
                        answer += '\n';
                    }else if(ddtmp[0]=='"url"'){
                        answer = answer + 'www.acmicpc.net' + ddtmp[1] + '\n';
                    }
                }else{
                    var type = dtmp[i].replace(/"/g,'').replace(/types:\[/g,'').replace(/\]/g,'')
                    types += (type+',')
                }
            }
            if(types.substr(0,types.length-1).length>0){
                answer += ( '분류: '+types.substr(0,types.length-1) + '\n')
            }
            Log.d(types.substr(0,types.length-1).length,true)
            // for(d of dtmp){
            //     var ddtmp = d.split(':');
            //     ddtmp[1] = ddtmp[1].replace(/"/g,'');
            //     if(ddtmp[0]=='"level"'){
            //         answer += ddtmp[1];
            //         answer += ' :';
            //     }else if(ddtmp[0]=='"name"'){
            //         answer += ddtmp[1];
            //         answer += '\n';
            //     }else if(ddtmp[0]=='"url"'){
            //         answer = answer + 'www.acmicpc.net' + ddtmp[1] + '\n';
            //     }
            // }
        }
        return answer;
    }else if(typeSet.includes(code)){
        code = typeCode[code]+'/one'
        var probJson = ajaxType(code);
        answer = probJson.replace(/(<([^>]+)>)/g,"");
        var data = answer.split("probs")
        for(d of data){
            Log.d(d,true)
        }
        // Log.d(answer,true)
    }

    switch (code) {
        case '사용법':
        case '메뉴':
            answer = menu();
            break;
        default:
            answer = '';
            break;
    }
    return answer;
};
var menu = function(){
    var result = '';
    result += '\t사용법\n';
    result += '"/티어 1~5" 로 사용이 가능합니다.\n';
    result += 'ex) /실버 or /실버 1';
    return result;
};
var ajax = function(tier,level){
    // Log.d(level,true);
    var url ="http://35.224.173.5:8080/boj/random/" + (level==''? tier:tier+'/'+level);
    var result = Utils.getWebText(url);
    return result;
};
var ajaxType = function(type){
    // Log.d(level,true);
    var url ="http://35.224.173.5:8080/api/" + type;
    var result = Utils.getWebText(url);
    return result;
};