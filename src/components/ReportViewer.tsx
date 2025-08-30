import React from 'react'
import { FileText, Download, Image, BarChart3, CheckCircle, XCircle, Clock } from 'lucide-react'

const ReportViewer = () => {
  const reportFiles = [
    {
      name: 'ZieglerAerospace_TestReport_2024-01-15_14-30-25.html',
      type: 'HTML Report',
      size: '2.4 MB',
      status: 'generated',
      description: 'Comprehensive test execution report with screenshots and detailed logs'
    },
    {
      name: 'test_results_2024-01-15_14-30-25.csv',
      type: 'Test Results',
      size: '45 KB',
      status: 'generated',
      description: 'Detailed test execution results and status information'
    },
    {
      name: 'product_data_2024-01-15_14-30-25.csv',
      type: 'Product Data',
      size: '12 KB',
      status: 'generated',
      description: 'Captured product information including names, prices, and URLs'
    },
    {
      name: 'cart_data_2024-01-15_14-30-25.csv',
      type: 'Cart Data',
      size: '8 KB',
      status: 'generated',
      description: 'Shopping cart verification data and item details'
    },
    {
      name: 'validation_results_2024-01-15_14-30-25.csv',
      type: 'Validation Data',
      size: '15 KB',
      status: 'generated',
      description: 'Negative testing results and validation error information'
    }
  ]

  const testMetrics = [
    { label: 'Total Test Scenarios', value: '5', icon: <BarChart3 className="h-5 w-5 text-aerospace-600" /> },
    { label: 'Passed Tests', value: '5', icon: <CheckCircle className="h-5 w-5 text-green-600" /> },
    { label: 'Failed Tests', value: '0', icon: <XCircle className="h-5 w-5 text-red-600" /> },
    { label: 'Execution Time', value: '4m 32s', icon: <Clock className="h-5 w-5 text-yellow-600" /> }
  ]

  const getFileIcon = (type: string) => {
    switch (type) {
      case 'HTML Report':
        return <FileText className="h-5 w-5 text-blue-600" />
      case 'Test Results':
      case 'Product Data':
      case 'Cart Data':
      case 'Validation Data':
        return <Download className="h-5 w-5 text-green-600" />
      default:
        return <FileText className="h-5 w-5 text-gray-600" />
    }
  }

  return (
    <div className="space-y-8">
      {/* Test Metrics */}
      <div className="grid grid-cols-1 md:grid-cols-4 gap-6">
        {testMetrics.map((metric, index) => (
          <div key={index} className="bg-white rounded-xl shadow-sm border border-gray-200 p-6 text-center">
            <div className="flex items-center justify-center mb-3">
              {metric.icon}
            </div>
            <div className="text-2xl font-bold text-gray-900 mb-1">{metric.value}</div>
            <div className="text-sm text-gray-600">{metric.label}</div>
          </div>
        ))}
      </div>

      {/* Generated Reports */}
      <div className="bg-white rounded-xl shadow-sm border border-gray-200 p-6">
        <div className="flex items-center justify-between mb-6">
          <div>
            <h2 className="text-xl font-bold text-gray-900">📊 Generated Reports & Data</h2>
            <p className="text-gray-600">Comprehensive test documentation and exported data files</p>
          </div>
          
          <button className="btn-primary flex items-center space-x-2">
            <Download className="h-4 w-4" />
            <span>Download All</span>
          </button>
        </div>

        <div className="space-y-4">
          {reportFiles.map((file, index) => (
            <div key={index} className="flex items-center justify-between p-4 border border-gray-200 rounded-lg hover:bg-gray-50 transition-colors">
              <div className="flex items-center space-x-4">
                {getFileIcon(file.type)}
                <div>
                  <div className="font-medium text-gray-900">{file.name}</div>
                  <div className="text-sm text-gray-600">{file.description}</div>
                </div>
              </div>
              
              <div className="flex items-center space-x-4">
                <div className="text-right">
                  <div className="text-sm font-medium text-gray-900">{file.type}</div>
                  <div className="text-xs text-gray-500">{file.size}</div>
                </div>
                
                <span className="status-badge status-pass">
                  Generated
                </span>
                
                <button className="p-2 text-gray-400 hover:text-gray-600 transition-colors">
                  <Download className="h-4 w-4" />
                </button>
              </div>
            </div>
          ))}
        </div>
      </div>

      {/* Sample Report Preview */}
      <div className="bg-white rounded-xl shadow-sm border border-gray-200 p-6">
        <h3 className="text-lg font-semibold text-gray-900 mb-4">📋 Sample Report Preview</h3>
        
        <div className="bg-gray-50 rounded-lg p-6 border-l-4 border-aerospace-500">
          <div className="space-y-4">
            <div className="flex items-center justify-between">
              <h4 className="font-semibold text-gray-900">🧪 Homepage & Category Verification</h4>
              <span className="status-badge status-pass">PASSED</span>
            </div>
            
            <div className="space-y-2 text-sm">
              <div className="flex items-center space-x-2">
                <CheckCircle className="h-4 w-4 text-green-500" />
                <span className="text-gray-700">Navigate to automation test store homepage</span>
              </div>
              <div className="flex items-center space-x-2">
                <CheckCircle className="h-4 w-4 text-green-500" />
                <span className="text-gray-700">Detect and print all main category names dynamically</span>
              </div>
              <div className="flex items-center space-x-2">
                <CheckCircle className="h-4 w-4 text-green-500" />
                <span className="text-gray-700">Successfully detected 6 categories</span>
              </div>
              <div className="flex items-center space-x-2">
                <CheckCircle className="h-4 w-4 text-green-500" />
                <span className="text-gray-700">Category contains at least 3 visible products - requirement met</span>
              </div>
            </div>
            
            <div className="mt-4 p-3 bg-white rounded border">
              <div className="text-xs text-gray-500 mb-1">Captured Data:</div>
              <div className="font-mono text-xs text-gray-700">
                Categories: Makeup, Skincare, Fragrance, Men, Hair Care, Books<br/>
                Selected: Skincare | Products Found: 8 | Requirement: ≥3 ✅
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* Screenshots Gallery */}
      <div className="bg-white rounded-xl shadow-sm border border-gray-200 p-6">
        <h3 className="text-lg font-semibold text-gray-900 mb-4">📸 Screenshot Documentation</h3>
        
        <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
          {[
            { name: 'homepage_verification.png', description: 'Homepage category detection' },
            { name: 'product_selection.png', description: 'Random product selection process' },
            { name: 'cart_verification.png', description: 'Shopping cart validation' },
            { name: 'checkout_workflow.png', description: 'Checkout form automation' },
            { name: 'validation_testing.png', description: 'Negative scenario testing' }
          ].map((screenshot, index) => (
            <div key={index} className="border border-gray-200 rounded-lg p-4 hover:shadow-md transition-shadow">
              <div className="flex items-center space-x-2 mb-2">
                <Image className="h-4 w-4 text-gray-600" />
                <span className="font-medium text-gray-900 text-sm">{screenshot.name}</span>
              </div>
              <div className="text-xs text-gray-600">{screenshot.description}</div>
              <div className="mt-3 bg-gray-100 rounded aspect-video flex items-center justify-center">
                <Image className="h-8 w-8 text-gray-400" />
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  )
}

export default ReportViewer