import React from 'react'
import { Folder, File, Code, Database, Settings, TestTube } from 'lucide-react'

const ProjectStructure = () => {
  const projectStructure = [
    {
      name: 'src',
      type: 'folder',
      icon: <Folder className="h-4 w-4 text-blue-500" />,
      children: [
        {
          name: 'main/java/com/ziegler/aerospace',
          type: 'folder',
          icon: <Folder className="h-4 w-4 text-blue-500" />,
          children: [
            {
              name: 'pages',
              type: 'folder',
              icon: <Folder className="h-4 w-4 text-green-500" />,
              description: 'Page Object Model classes',
              children: [
                { name: 'HomePage.java', type: 'file', icon: <Code className="h-4 w-4 text-orange-500" />, description: 'Homepage interactions and category detection' },
                { name: 'ProductPage.java', type: 'file', icon: <Code className="h-4 w-4 text-orange-500" />, description: 'Product selection and cart addition' },
                { name: 'CartPage.java', type: 'file', icon: <Code className="h-4 w-4 text-orange-500" />, description: 'Shopping cart verification and checkout' },
                { name: 'CheckoutPage.java', type: 'file', icon: <Code className="h-4 w-4 text-orange-500" />, description: 'Checkout workflow and form automation' },
                { name: 'RegistrationPage.java', type: 'file', icon: <Code className="h-4 w-4 text-orange-500" />, description: 'Registration and negative testing' }
              ]
            },
            {
              name: 'utils',
              type: 'folder',
              icon: <Folder className="h-4 w-4 text-purple-500" />,
              description: 'Utility and helper classes',
              children: [
                { name: 'DriverManager.java', type: 'file', icon: <Settings className="h-4 w-4 text-gray-600" />, description: 'WebDriver lifecycle management' },
                { name: 'ReportManager.java', type: 'file', icon: <Database className="h-4 w-4 text-blue-600" />, description: 'ExtentReports and screenshot handling' },
                { name: 'TestDataManager.java', type: 'file', icon: <Database className="h-4 w-4 text-blue-600" />, description: 'CSV export and data management' }
              ]
            }
          ]
        },
        {
          name: 'test/java/com/ziegler/aerospace/tests',
          type: 'folder',
          icon: <Folder className="h-4 w-4 text-red-500" />,
          children: [
            { name: 'ECommerceWorkflowTest.java', type: 'file', icon: <TestTube className="h-4 w-4 text-red-500" />, description: 'Main test class with all scenarios' }
          ]
        }
      ]
    },
    {
      name: 'Configuration Files',
      type: 'folder',
      icon: <Folder className="h-4 w-4 text-gray-500" />,
      children: [
        { name: 'pom.xml', type: 'file', icon: <File className="h-4 w-4 text-red-600" />, description: 'Maven dependencies and build configuration' },
        { name: 'build.gradle', type: 'file', icon: <File className="h-4 w-4 text-green-600" />, description: 'Gradle alternative build configuration' },
        { name: 'testng.xml', type: 'file', icon: <File className="h-4 w-4 text-yellow-600" />, description: 'TestNG suite configuration' }
      ]
    }
  ]

  const renderTreeItem = (item: any, level = 0) => {
    const indent = level * 24

    return (
      <div key={item.name} className="space-y-1">
        <div 
          className="flex items-center space-x-2 py-2 px-3 rounded-lg hover:bg-gray-50 transition-colors"
          style={{ marginLeft: `${indent}px` }}
        >
          {item.icon}
          <span className={`font-medium ${item.type === 'folder' ? 'text-gray-900' : 'text-gray-700'}`}>
            {item.name}
          </span>
          {item.description && (
            <span className="text-sm text-gray-500 ml-2">- {item.description}</span>
          )}
        </div>
        
        {item.children && (
          <div className="space-y-1">
            {item.children.map((child: any) => renderTreeItem(child, level + 1))}
          </div>
        )}
      </div>
    )
  }

  return (
    <div className="space-y-8">
      {/* Project Overview */}
      <div className="bg-white rounded-xl shadow-sm border border-gray-200 p-8">
        <h2 className="text-2xl font-bold text-gray-900 mb-4">🏗️ Project Architecture</h2>
        <p className="text-gray-600 mb-6">
          Clean, modular architecture following Page Object Model (POM) design pattern for maintainable and scalable test automation.
        </p>
        
        <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
          <div className="text-center p-4 bg-aerospace-50 rounded-lg">
            <Code className="h-8 w-8 text-aerospace-600 mx-auto mb-2" />
            <div className="font-semibold text-gray-900">Page Objects</div>
            <div className="text-sm text-gray-600">5 specialized page classes</div>
          </div>
          
          <div className="text-center p-4 bg-green-50 rounded-lg">
            <Settings className="h-8 w-8 text-green-600 mx-auto mb-2" />
            <div className="font-semibold text-gray-900">Utilities</div>
            <div className="text-sm text-gray-600">3 helper classes</div>
          </div>
          
          <div className="text-center p-4 bg-red-50 rounded-lg">
            <TestTube className="h-8 w-8 text-red-600 mx-auto mb-2" />
            <div className="font-semibold text-gray-900">Test Suite</div>
            <div className="text-sm text-gray-600">Comprehensive workflow tests</div>
          </div>
        </div>
      </div>

      {/* File Structure */}
      <div className="bg-white rounded-xl shadow-sm border border-gray-200 p-6">
        <h3 className="text-lg font-semibold text-gray-900 mb-4">📁 File Structure</h3>
        
        <div className="bg-gray-50 rounded-lg p-4">
          {projectStructure.map(item => renderTreeItem(item))}
        </div>
      </div>

      {/* Key Features */}
      <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
        <div className="bg-white rounded-xl shadow-sm border border-gray-200 p-6">
          <h3 className="text-lg font-semibold text-gray-900 mb-4">🎯 Design Patterns</h3>
          <div className="space-y-3">
            <div className="flex items-start space-x-3">
              <div className="w-2 h-2 bg-aerospace-400 rounded-full mt-2"></div>
              <div>
                <div className="font-medium text-gray-900">Page Object Model (POM)</div>
                <div className="text-sm text-gray-600">Clean separation of test logic and page interactions</div>
              </div>
            </div>
            <div className="flex items-start space-x-3">
              <div className="w-2 h-2 bg-aerospace-400 rounded-full mt-2"></div>
              <div>
                <div className="font-medium text-gray-900">Factory Pattern</div>
                <div className="text-sm text-gray-600">WebDriver and PageFactory initialization</div>
              </div>
            </div>
            <div className="flex items-start space-x-3">
              <div className="w-2 h-2 bg-aerospace-400 rounded-full mt-2"></div>
              <div>
                <div className="font-medium text-gray-900">Singleton Pattern</div>
                <div className="text-sm text-gray-600">Report and data management</div>
              </div>
            </div>
          </div>
        </div>

        <div className="bg-white rounded-xl shadow-sm border border-gray-200 p-6">
          <h3 className="text-lg font-semibold text-gray-900 mb-4">🛡️ Best Practices</h3>
          <div className="space-y-3">
            <div className="flex items-start space-x-3">
              <div className="w-2 h-2 bg-green-400 rounded-full mt-2"></div>
              <div>
                <div className="font-medium text-gray-900">Explicit Waits</div>
                <div className="text-sm text-gray-600">WebDriverWait for reliable element interactions</div>
              </div>
            </div>
            <div className="flex items-start space-x-3">
              <div className="w-2 h-2 bg-green-400 rounded-full mt-2"></div>
              <div>
                <div className="font-medium text-gray-900">Exception Handling</div>
                <div className="text-sm text-gray-600">Comprehensive try-catch with meaningful errors</div>
              </div>
            </div>
            <div className="flex items-start space-x-3">
              <div className="w-2 h-2 bg-green-400 rounded-full mt-2"></div>
              <div>
                <div className="font-medium text-gray-900">Thread Safety</div>
                <div className="text-sm text-gray-600">ThreadLocal driver management for parallel execution</div>
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* Build Commands */}
      <div className="bg-white rounded-xl shadow-sm border border-gray-200 p-6">
        <h3 className="text-lg font-semibold text-gray-900 mb-4">⚡ Build & Execution Commands</h3>
        
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
          <div>
            <h4 className="font-medium text-gray-900 mb-3">Maven Commands</h4>
            <div className="space-y-2 font-mono text-sm">
              <div className="bg-gray-100 p-3 rounded-lg">mvn clean install</div>
              <div className="bg-gray-100 p-3 rounded-lg">mvn test</div>
              <div className="bg-gray-100 p-3 rounded-lg">mvn test -Dtest=ECommerceWorkflowTest</div>
            </div>
          </div>
          
          <div>
            <h4 className="font-medium text-gray-900 mb-3">Gradle Commands</h4>
            <div className="space-y-2 font-mono text-sm">
              <div className="bg-gray-100 p-3 rounded-lg">./gradlew build</div>
              <div className="bg-gray-100 p-3 rounded-lg">./gradlew test</div>
              <div className="bg-gray-100 p-3 rounded-lg">./gradlew runTests</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  )
}

export default ProjectStructure