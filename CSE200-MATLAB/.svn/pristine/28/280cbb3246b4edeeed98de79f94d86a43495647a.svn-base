function preds=knnclassifier(xTr,yTr,xTe,k);
% function preds=knnclassifier(xTr,yTr,xTe,k);
%
% k-nn classifier 
%
% Input:
% xTr = dxn input matrix with n column-vectors of dimensionality d
% xTe = dxm input matrix with n column-vectors of dimensionality d
% k = number of nearest neighbors to be found
%
% Output:
% preds = predicted labels, ie preds(i) is the predicted label of xTe(:,i)
%


% output random result as default (to be replaced)
[d,n]=size(xTe);
[d,ntr]=size(xTr);
if k>ntr
    k=ntr;
end
un=unique(yTr);
preds=un(ceil(rand(1,n)*length(un)));

%% TODO: fill in code here

