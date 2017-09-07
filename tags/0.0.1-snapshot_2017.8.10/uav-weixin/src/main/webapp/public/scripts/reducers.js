import { combineReducers } from 'redux'
import applyRecord from 'scripts/apply-record/reducer'
import airApply from 'scripts/airspace/reducer'

export default combineReducers({
	applyRecord,
	airApply,
})