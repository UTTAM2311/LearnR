function [centroids, idx] = colorCluster(imagePath, noOfColors)
%COLORCLUSTER Calculates the RGB image or colormap's dominant color(s).
%   COLORCLUSTER clusters the existing colors in the image to their 
%	nearest basic color. Returns a matrix of pixel counts of 
%   colors in the descending order.
%
%   [centroids, idx] = COLORCLUSTER(imagePath, noOfColors) clusters the image 
%	pixels and returns the cluster centroids;
%

% Read Image
X = double(imread(imagePath));
origSize = size(X);

fprintf('\nImage dimensions .. ');
disp(origSize);

% Determine if input includes a 3-D array 
if (ndims(X)==3), % RGB
  	
	% Shape input matrix so that it is a n x 3 array and initialize output matrix  
	X = reshape(X(:), origSize(1)*origSize(2), 3);
	sizeOutput = [origSize(1), origSize(2)];

	% plotting image pixels
	figure;
	plot3(X);

	% init centroids
	K = noOfColors; % no of centroids 
	initial_centroids = kMeansInitCentroids(X, K);

	% K Means for centroids
	[centroids, idx] = runkMeans(X, initial_centroids, 5);


	hold off;

end




% Random initialize centroids
function centroids = kMeansInitCentroids(X, K)
	fprintf('Initializing centroids randomly - no. of centroids : %d\n', K );
	
	% Initialize centroids to zeros
	centroids = zeros(K, size(X, 2));
	
	% random permutation on indexes
	randidx = randperm(size(X, 1));

	% picking those random 10 points as centroids
	centroids = X(randidx(1:K), :);
end


% K-Means algorithm
function [centroids, idx] = runkMeans(X, initial_centroids, max_iters)

	% Initialize values
	[m n] = size(X);
	K = size(initial_centroids, 1);
	centroids = initial_centroids;
	previous_centroids = centroids;
	idx = zeros(m, 1);

	% Run K-Means
	for i=1:max_iters,
		
		% Output progress
		fprintf('\nK-Means iteration %d/%d...\n', i, max_iters);
		if exist('OCTAVE_VERSION')
		    fflush(stdout);
		end
		
		% For each example in X, assign it to the closest centroid
		idx = findClosestCentroids(X, centroids);
		
		% Given the memberships, compute new centroids
		centroids = computeCentroids(X, idx, K);
	end


end


% Find update the closest centroid to each pixel
function idx = findClosestCentroids(X, centroids),
	fprintf('Finding closest centroids .. \n');

	% Set K
	K = size(centroids, 1);

	% initialize variables
	idx = zeros(size(X,1), 1);
	m = size(X, 1);

	for i = 1:m,
		x = X(i, :);
	
		% measuring distances to the centroids
		for j = 1:size(centroids, 1);
			dist(j, i) = sum( (centroids(j, :) - x).^2 );
		end
	end
	
	% minimum distances
	[minX, idx] = min(dist);

end

% Computes and updates the cluster centroids
function centroids = computeCentroids(X, idx, K)
	fprintf('Computing and updating closest centroids .. \n');

	% input data matrix
	[m n] = size(X);

	% initialize variables
	centroids = zeros(K, n);
	clust_count = zeros(K, 1);

	for i = 1:m,
	
		% for each training examples
		x = X(i, :);
		x_cent = idx(i);

		% Centroid preparation
		centroids(x_cent, :) = centroids(x_cent, :) + x;
		clust_count(x_cent) = clust_count(x_cent) + 1;

	end

	% Centroid mean
	for i = 1:K,
		if( clust_count(i) != 0 ),
			centroids(i, :) = centroids(i, :) ./ clust_count(i);
		endif
	end

end




