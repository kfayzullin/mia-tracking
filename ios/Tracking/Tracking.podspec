Pod::Spec.new do |s|
  s.name         = "Tracking"
  s.version      = "0.0.1"
  s.summary      = "Tracking Layer"

  s.platform     = :ios, '7.0'
  s.source_files  = 'Tracking', 'Tracking/**/*.{h,m}'
  s.public_header_files = 'Tracking/**/*.h'
  s.framework    = 'SystemConfiguration'
  s.requires_arc = true

  s.resource_bundles = {
    'TrackingLayer' => ['Tracking/assets/*.*']
  }

  s.dependency 'React'
  s.dependency 'Mixpanel'
end

