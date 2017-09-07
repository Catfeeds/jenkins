import Tool from 'utils/tool'
import { combineReducers } from 'redux'
import { actionTypes, initState } from './action'


export default function( state = initState, action ){
	switch( action.type ){
		case actionTypes.handleChange:
			return Tool.updateIn( state, action.name, action.data );
		case actionTypes.loadAirspace:
			return Object.assign( {}, state, { asData : action.data } )
		case actionTypes.loadLanding:
			return Object.assign( {}, state, { landingData : action.data } )
		case actionTypes.resetForm:
			return initState;
		default:
			return state;
	}
}
