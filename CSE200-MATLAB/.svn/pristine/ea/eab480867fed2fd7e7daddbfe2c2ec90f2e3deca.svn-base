function visualKNN()

%% Visual interface for CSE200 final project
%% Performing k-nearest neighbor classification on digits
% Look for 2 TODO items!



%% set parameters 
% TODO: replace the following lines with code that queries the user which 
% dataset to use and to select the parameter k (number of nearest neighbors):
% dataset needs to be specified as a string (either 'digits' or 'faces').
% k needs to be an integer between 1 and 10
% Use a sentinel to ensure the correct input! If the user specifies
% incorrect input your code shouldautomatically query the user again
% providing an instructive error message. 

dataset='digits';   % to be replaced
%dataset='faces';   % to be replaced
k=3;                % to be replaced



%% load data
% HANDWRITTEN DIGIT RECOGNITION
switch dataset
case 'digits'
	load('digits');
	dx=16;  % image dimensions
	dy=16;

% FACE IDENTIFICATION
case 'faces'
	load('faces');
	dx=31;  % image dimensions
	dy=38;
end;

%% Set number of training examples
ntr=length(yTr); % number of training data points (Must be smaller than length(yTr))
xTr=xTr(:,1:ntr);
  


%% Initialization
% create new figure
figure(1);
clf;
c=k+1;  % number of columns (function of k)
r=5;    % number of rows in display
for i=1:r*c
 subplot(r,c,i);
 M(i).image=imagesc(zeros(dy,dx));
 colormap gray;
 as=axis();
% T(i)=text((as(4)-as(3))/2,(as(2)-as(1))/2,[' ' num2str(i)],'FontSize',18,'FontWeight','bold','FontName','Times','Color',[0,1,1]);
 T(i)=text(5,0,[' ' num2str(i)],'FontSize',18,'FontWeight','bold','FontName','Times','Color',[0,1,1],'VerticalAlignment','top');
 set(gca,'XTick',[],'YTick',[],'FontWeight','bold');
 S(i)=gca;
end;
%
% add axis labels
subplot(r,c,(r-1)*c+1);
xlabel('TEST');
for i=1:k
 subplot(r,c,(r-1)*c+1+i);
 set(gca,'FontWeight','Bold');
 xlabel(['Neighbor ' num2str(i)]);
end;

 
drawnow;

% do the classification
for n=1:r:length(yTe) % iterate over test points

	for nj=1:r
		ex=n+nj-1;
		% draw the new test image		
		set(M((nj-1)*c+1).image,'CData',reshape(xTe(:,ex),dy,dx));
		set(get(S((nj-1)*c+1),'Ylabel'),'String',num2str(yTe(ex)),'FontWeight','bold');
        
		% TODO: draw k-nearest neighbors
		inds=randi(size(xTr,2), 1,3);  % to be replaced
        
		for m=1:k
			set(M((nj-1)*c+1+m).image,'CData',reshape(xTr(:,inds(m)),dy,dx));
			set(T((nj-1)*c+1+m),'String',num2str(yTr(inds(m))));
		end;
		% set classification accuracy
		pred=knnclassifier(xTr,yTr,xTe(:,ex),k);
		set(T((nj-1)*c+1),'String',num2str(pred));
		if pred==yTe(ex),set(T((nj-1)*c+1),'Color',[0 1 0]); else,set(T((nj-1)*c+1),'Color',[1 0 0]);end;
	 end;
		drawnow;
	pause;
end;
