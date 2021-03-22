import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICentralSwitch, defaultValue } from 'app/shared/model/central-switch.model';

export const ACTION_TYPES = {
  FETCH_CENTRALSWITCH_LIST: 'centralSwitch/FETCH_CENTRALSWITCH_LIST',
  FETCH_CENTRALSWITCH: 'centralSwitch/FETCH_CENTRALSWITCH',
  CREATE_CENTRALSWITCH: 'centralSwitch/CREATE_CENTRALSWITCH',
  UPDATE_CENTRALSWITCH: 'centralSwitch/UPDATE_CENTRALSWITCH',
  DELETE_CENTRALSWITCH: 'centralSwitch/DELETE_CENTRALSWITCH',
  RESET: 'centralSwitch/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICentralSwitch>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type CentralSwitchState = Readonly<typeof initialState>;

// Reducer

export default (state: CentralSwitchState = initialState, action): CentralSwitchState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CENTRALSWITCH_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CENTRALSWITCH):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_CENTRALSWITCH):
    case REQUEST(ACTION_TYPES.UPDATE_CENTRALSWITCH):
    case REQUEST(ACTION_TYPES.DELETE_CENTRALSWITCH):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_CENTRALSWITCH_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CENTRALSWITCH):
    case FAILURE(ACTION_TYPES.CREATE_CENTRALSWITCH):
    case FAILURE(ACTION_TYPES.UPDATE_CENTRALSWITCH):
    case FAILURE(ACTION_TYPES.DELETE_CENTRALSWITCH):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CENTRALSWITCH_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_CENTRALSWITCH):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_CENTRALSWITCH):
    case SUCCESS(ACTION_TYPES.UPDATE_CENTRALSWITCH):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_CENTRALSWITCH):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/central-switches';

// Actions

export const getEntities: ICrudGetAllAction<ICentralSwitch> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_CENTRALSWITCH_LIST,
    payload: axios.get<ICentralSwitch>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<ICentralSwitch> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CENTRALSWITCH,
    payload: axios.get<ICentralSwitch>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ICentralSwitch> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CENTRALSWITCH,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICentralSwitch> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CENTRALSWITCH,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICentralSwitch> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CENTRALSWITCH,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
