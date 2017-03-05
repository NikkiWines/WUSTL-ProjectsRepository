

%% TODO: measure the time your prediction takes
%% TODO: fill in your print statements



fprintf('Face Recognition: (1-nn)\n')
load('faces');

preds=knnclassifier(xTr,yTr,xTe,1);
result=analyze('acc',yTe,preds);



fprintf('\n');
fprintf('Handwritten digits Recognition: (3-nn)\n')
load('digits');

preds=knnclassifier(xTr,yTr,xTe,3);
result=analyze('acc',yTe,preds);





