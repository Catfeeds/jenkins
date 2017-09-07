import Tool from 'utils/tool'
export const actionTypes = {
	handleChange : "apply-record/form3/handleChange",
	loadLanding : "apply-record/loadLanding",
	loadAirspace : "apply-record/loadAirspace",
	resetForm : "apply-record/resetForm"
}
export const initState = {
	asData : [],
	landingData : [],
	//计划类型
	projectType : 0,
	projectDesc : "",
	//使用机型
	planes : [
		// { 
		// 	name : "",
		// 	number : "",
		// }
	],
	planeOtherName : "",
	//使用时间
	startDate : "",
	startTime : undefined,
	endTime : undefined,
	// 空域批文编号
	applyOfficialNo : "",
	//空域及范围
	airInfo : [
		// {
		// 	centers : [
		// 		{ lng : "", lat : ""}
		// 	],
		// 	high : "",
		// 	location : "",
		// 	scopeType : 2,
		// }
	],
	//使用起降场
	landingInfo : [
		// {
		// 	id : "",
		// 	lng : "",
		// 	lat : "",
		// 	location : "",
		// 	name : ""
		// }
	],
	//单位联系人
	companyContactName : "",
	//单位联系人号码
	companyContactPhone : "",
	//现场联系人
	fieldContactName : "",
	//现场联系人号码
	fieldContactPhone : "",
	//紧急联系人
	jjContactName : "",
	//紧急联系人号码
	jjContactPhone : "",
	//气象条件
	weatherCondition : "",
	//其他说明事项
	remark : "",
}
export function handleChange( name, data ){
	return {
		type : actionTypes.handleChange,
		name,
		data,
	}
}

export function loadAirspace(){
	return dispatch => Tool.get(`${ config.ajaxPath }/metadata/airspace`)
		.then( json => {
			let asData = {};
			json.data.forEach( v => {
				if( asData[v.provinceCode] ){
					if( asData[v.provinceCode]["items"][v.cityCode] ){
						if( asData[v.provinceCode]["items"][v.cityCode]["items"][v.areaCode] ){
							if( !asData[v.provinceCode]["items"][v.cityCode]["items"][v.areaCode]["_items"][v.high] ){
								asData[v.provinceCode]["items"][v.cityCode]["items"][v.areaCode]["_items"][v.high] = {
									break : true,
									name : v.location,
									high : v.high,
									scopeType : v.scopeType,
									id : v.id,
									text : `${ v.location }（真高：${ v.high }（含）以下）`
								}
							}
						}else{
							asData[v.provinceCode]["items"][v.cityCode]["items"][v.areaCode] = { 
								name : v.areaName,
								code : v.areaCode,
								_items : {
									[v.high] : {
										break : true,
										name : v.location,
										high : v.high,
										scopeType : v.scopeType,
										id : v.id,
										text : `${ v.location }（真高：${ v.high }（含）以下）`
									}
								}
							}
						}
					}else{
						asData[v.provinceCode]["items"][v.cityCode] = {
							name : v.cityName,
							code : v.cityCode,
							items : {
								[v.areaCode] : {
									name : v.areaName,
									code : v.areaCode,
									_items : {
										[v.high] : {
											break : true,
											name : v.location,
											high : v.high,
											scopeType : v.scopeType,
											id : v.id,
											text : `${ v.location }（真高：${ v.high }（含）以下）`
										}
									}
								}
							}
						}
					}
				}else{
					asData[v.provinceCode] = {
							name : v.provinceName,
							code : v.provinceCode,
							items : {
								[v.cityCode] : {
									name : v.cityName,
									code : v.cityCode,
									items : {
										[v.areaCode] : {
											name : v.areaName,
											code : v.areaCode,
											_items : {
												[v.high] : {
													break : true,
													name : v.location,
													high : v.high,
													scopeType : v.scopeType,
													id : v.id,
													text : `${ v.location }（真高：${ v.high }（含）以下）`
												}
											}
										}
									}
								}
							}
						}
				}
			})
			function parse( obj ){
				let _obj,
					items = [];
				Object.keys(obj).forEach( key => {
					if( key === "items" || key === "items" )
						return;
					_obj = {
						name : obj[key].name,
						code : obj[key].code,
					};
					if( typeof obj[key].items === "object" && !obj[key].items.break ){
						_obj.items = parse( obj[key].items )
					}else if( typeof obj[key]._items === "object" )
						_obj._items = parse( obj[key]._items )
					else
						Object.assign( _obj, obj[key] )
					items.push(_obj);
				})
				return items;	
			}
			return parse( asData )
		})
		.then( data => dispatch({
			type : actionTypes.loadAirspace,
			data,
		}))
}

export function loadLanding(){
	return dispatch => Tool.get(`${ config.ajaxPath}/metadata/landings`)
	.then( json => json.data.map( v => ({
		label : v.name,
		value : v.id,
		location : v.location,
		lat : v.lat,
		lng : v.lng,
	})) )
	.then( data => dispatch({
		type : actionTypes.loadLanding,
		data,
	}))
}

export function resetForm(){
	return {
		type : actionTypes.resetForm
	}
}