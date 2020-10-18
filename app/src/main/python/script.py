from sklearn.external import joblib
def predict():
    pred = joblib.load('JUSL_RF_model.sav')
    result = pred.predict([[33,27,18,0,0,0,0,0,0,0,0,0,0,0,0,0,0]])
    return result;